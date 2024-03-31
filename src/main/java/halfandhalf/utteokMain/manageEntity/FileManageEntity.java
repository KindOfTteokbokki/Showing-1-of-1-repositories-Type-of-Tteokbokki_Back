package halfandhalf.utteokMain.manageEntity;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class FileManageEntity {
    private String file_path;
    private String file_original_name;
    private String file_masking_name;

    public FileManageEntity() {
    }

    public FileManageEntity(String file_path, String file_original_name, String file_masking_name) {
        this.file_path = file_path;
        this.file_original_name = file_original_name;
        this.file_masking_name = file_masking_name;
    }
}
