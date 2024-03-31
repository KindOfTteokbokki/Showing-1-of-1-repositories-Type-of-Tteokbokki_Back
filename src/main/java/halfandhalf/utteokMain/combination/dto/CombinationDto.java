package halfandhalf.utteokMain.combination.dto;

import halfandhalf.utteokMain.combination.entity.CombinationEntity;
import halfandhalf.utteokMain.combination.repository.CombinationRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class CombinationDto {
    private Long combination_seq;
    private String content;
    private String file_path;
    private String file_original_name;
    private String file_masking_name;

    public CombinationDto(Optional<CombinationEntity> combinationEntity) {
        combinationEntity.ifPresent(entity -> {
            this.combination_seq = entity.getId();
            this.content = entity.getContent();
            this.file_path = entity.getFileManageEntity().getFile_path();
            this.file_original_name = entity.getFileManageEntity().getFile_original_name();
            this.file_masking_name = entity.getFileManageEntity().getFile_masking_name();
        });
    }
}
