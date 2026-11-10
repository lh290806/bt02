package murach.email;

import java.util.*;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import murach.business.*;
import murach.data.*;

public class EmailListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/index.html";

        String action = request.getParameter("action");
        if (action == null) {
            action = "join";
        }

        if (action.equals("join")) {
            url = "/index.html";
        }
        else if (action.equals("add")) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String dob = request.getParameter("dob");

            // Xử lý radio button "How did you hear about us?"
            String hearAbout = request.getParameter("hearAbout");
            if ("search".equals(hearAbout)) hearAbout = "Search engine";
            else if ("word".equals(hearAbout)) hearAbout = "Word of mouth";
            else if ("social".equals(hearAbout)) hearAbout = "Social Media";
            else hearAbout = "Other";

            // Xử lý select box "Contact me by"
            String contactBy = request.getParameter("contactBy");
            if ("email_or_postal".equals(contactBy)) contactBy = "Email or postal mail";
            else if ("email".equals(contactBy)) contactBy = "Email";
            else contactBy = "Postal mail";

            // Xử lý mảng checkbox "Announcements"
            String[] ann = request.getParameterValues("announcements");
            String announcements = "None";
            if (ann != null && ann.length > 0) {
                List<String> list = new ArrayList<>();
                for (String item : ann) {
                    if ("like".equals(item)) {
                        list.add("YES, I'd like that.");
                    } else if ("email_announcements".equals(item)) {
                        list.add("YES, please send me email announcements.");
                    }
                }
                // Nối các mục đã chọn bằng dấu phẩy và khoảng trắng (hoặc "<br>" nếu muốn xuống dòng)
                announcements = String.join("<br>", list);
            }

            User user = new User(firstName, lastName, email, dob);
            Survey survey = new Survey(user, hearAbout, announcements, contactBy);

            // Gắn dữ liệu vào request để truyền sang file JSP hiển thị
            request.setAttribute("user", user);
            request.setAttribute("survey", survey);
            request.setAttribute("dob", dob);
            request.setAttribute("hearAbout", hearAbout);
            request.setAttribute("contactBy", contactBy);
            request.setAttribute("announcements", announcements);

            url = "/thanks.jsp";
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}