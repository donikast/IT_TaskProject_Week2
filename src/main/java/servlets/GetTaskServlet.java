package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Task;
import repository.TaskRepository;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/tasks/view")
public class GetTaskServlet extends HttpServlet {
    private TaskRepository taskRepository;
    @Override
    public void init() throws ServletException {
        taskRepository = TaskRepository.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();

        String id = req.getParameter("id");
        Task task = taskRepository.getTaskById(Integer.parseInt(id));
        if(task!=null) {
            pw.println("<html><body><p>");
            pw.println("Task found!");
            pw.println(task);
            pw.println("</p></body></html>");
            resp.setStatus(200);
        }
        else {
            pw.println("<html><body><p>");
            pw.println("Not found!");
            pw.println("</p></body></html>");
            resp.setStatus(404);
        }
        pw.close();
    }
}
