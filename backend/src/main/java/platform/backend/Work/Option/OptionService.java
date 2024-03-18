package platform.backend.Work.Option;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Service
public class OptionService {

    private final OptionRepository optionRepository;

    public Option addOption(Option option) {
        return optionRepository.save(option);
    }


}
