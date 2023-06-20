package com.example.DAMH.Controller;

import com.example.DAMH.Exception.CumtomException;
import com.example.DAMH.Model.Role;
import com.example.DAMH.Model.User;
import com.example.DAMH.Service.PasswordService;
import com.example.DAMH.Service.RegisterService;
import com.example.DAMH.Service.RoleService;
import com.example.DAMH.Service.UserService;
import com.example.DAMH.Util.Utilities;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PasswordService passwordService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private RoleService roleService;

    @GetMapping("/login")
    public String Formlogin () {
        return "auth/login";
    }
    @GetMapping("/")
    public String HomeForm() { return "home/home"; }

    @GetMapping("/register")
    public String Formregister() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String saveRegister(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        registerService.saveRegister(user);
        return "redirect:/login";
    }

    @GetMapping("/forgot_password")
    public String showForgotpassForm() {
        return "auth/forgot_pass";
    }

    public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("luxefashionhub@gmail.com", "Luxe Fashion Hub");
        helper.setTo(recipientEmail);

        String subject = "Link password";

        String content = "<p> Chào bạn,  </p>" + "<p> Bạn có một yêu cầu đặt lại mật khẩu.</p>"
                + "<p> Nhấn vào <a href=\"" + link + "\">đây</a> để đổi mật khẩu </p>"
                + "<p> Bỏ qua email này nếu bạn nhớ mật khẩu,"
                + " hoặc bạn không gửi yêu cầu này.</p>";

        helper.setSubject(subject);
        helper.setText(content, true);
        javaMailSender.send(message);
    }

    @PostMapping("/forgot_password")
    public String processForgotPass(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = RandomString.make(30);

        try {
            userService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utilities.getSiteURL(request) + "/reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "Chúng tôi đã gửi link đổi mật khẩu tới email của bạn. Nhấn vào.");
            return "thongbao/Success";

        }catch (CumtomException ex) {
            model.addAttribute("error", ex.getMessage());
            return "auth/forgot_pass";
        }catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Lỗi khi khi email");
            return "auth/forgot_pass";
        }
       /* return "auth/forgot_pass";*/
    }

    @GetMapping("/reset_password")
    public String showRestPasswordForm(@Param(value = "token") String token, Model model) {
        User user= userService.getUserByTokenforgotpassWord(token);
        model.addAttribute("token", token);

        if (user == null) {
            model.addAttribute("message", "Không có Token");
            return "message";
        }
        return "auth/reset_pass";
    }
    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = userService.getUserByTokenforgotpassWord(token);
        model.addAttribute("title", "reset your password");

        if(user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        }else {
            userService.updatePassword(user, password);

            model.addAttribute("message", "You have successfully changed your password.");
        }
        return "redirect:/login";
    }

    @GetMapping("/change_password/{id}")
    public String editPassword(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        String newPassword = null;
        if (user == null) {
            return "not-found";
        } else {
            model.addAttribute("user", user);
            model.addAttribute("newPassword", newPassword);
            return "auth/change_pass";
        }
    }

    @PostMapping("/change_password")
    public String savePassword(@ModelAttribute("user") User user, @ModelAttribute("newPassword") String newPassword) {
        User user2 = userService.getUserById(user.getId());
        if (user2 == null) {
            return "not-found";
        }

        if (!passwordEncoder.matches(user.getPassword(), user2.getPassword())) {
            return "auth/change_pass";
        }

        String encryptedPassword = passwordEncoder.encode(newPassword);

        user2.setPassword(encryptedPassword);
        userService.addUser(user2);

        return "redirect:/";
    }
}
