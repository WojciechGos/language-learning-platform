package platform.backend.Work.Task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.backend.claude.Integration.ClaudeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/generate")
    public ResponseEntity<Task> generateTask(@RequestBody TaskRequest request){
        System.out.println(request.topic() + " " + request.type());

        return ResponseEntity.ok(taskService.generateTask(request));
    }



}
