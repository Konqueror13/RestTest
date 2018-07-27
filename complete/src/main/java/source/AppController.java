package source;

import com.google.gson.*;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RestController
public class AppController {

    @RequestMapping("/getOlderTasks")
    @ResponseBody
    public String getOlderTasks()
    {
        JsonArray tasksArray = getAllTasks();

        try
        {
            JsonArray olderTasks = new JsonArray();
            for (int i=0; i<tasksArray.size(); i++)
            {
                String dateString = tasksArray.get(i).getAsJsonObject().get("CREATIONDATE").getAsString().substring(26, 45);
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_MONTH, -5);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date minusFourHours = calendar.getTime();
                Date inputDate = sdf.parse(dateString);

                if (inputDate.before(minusFourHours))
                {
                    olderTasks.add(tasksArray.get(i));
                }
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
            return "Tasks = " +olderTasks.size() + "\n" + gson.toJson(olderTasks);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return "no tasks older than 4 hours found";
    }

    @RequestMapping("/getTasksBySecuritySystem/{securitySystem}")
    @ResponseBody
    public String getTasksBySecuritySystem(@PathVariable("securitySystem") String securitySystem)
    {
        JsonArray tasksArray = getAllTasks();
        JsonArray tasksBySecuritySystem = new JsonArray();

        for (int i=0; i<tasksArray.size(); i++)
        {
            if (tasksArray.get(i).getAsJsonObject().get("SECURITYSYSTEM").getAsString().equals(securitySystem))
            {
                tasksBySecuritySystem.add(tasksArray.get(i));
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        return "Results = " + tasksBySecuritySystem.size() + "\n" + gson.toJson(tasksBySecuritySystem);
    }

    @RequestMapping("/getTaskByRequestId/{requestId}")
    @ResponseBody
    public String getTaskByRequestId(@PathVariable("requestId") String requestId)
    {
        JsonArray tasksArray = getAllTasks();

        for (int i=0; i<tasksArray.size(); i++)
        {
            if (tasksArray.get(i).getAsJsonObject().get("TASKID").getAsString().equals(requestId))
            {
                Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
                return gson.toJson(tasksArray.get(i));
            }
        }
        return "Request Not Found";
    }


    private JsonArray getAllTasks()
    {
        try
        {
            String fileName = "input.json";
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            File file = new File(classLoader.getResource(fileName).getPath().replace("%20", " "));
            String inputString = FileUtils.readFileToString(file);
            JsonParser parser = new JsonParser();
            JsonObject json = (JsonObject) parser.parse(inputString);

            return json.getAsJsonArray("tasks");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
