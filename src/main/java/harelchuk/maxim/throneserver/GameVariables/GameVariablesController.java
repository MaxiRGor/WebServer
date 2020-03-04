package harelchuk.maxim.throneserver.GameVariables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/variables")       //->variables
public class GameVariablesController {
    private GameVariablesRepository gameVariablesRepository;

    @Autowired
    GameVariablesController(GameVariablesRepository gameVariablesRepository) {
        this.gameVariablesRepository = gameVariablesRepository;
    }

    @GetMapping(path = "/get")
    public @ResponseBody
    List<GameVariables> getVariables() {
        return gameVariablesRepository.findAll();
    }

}
