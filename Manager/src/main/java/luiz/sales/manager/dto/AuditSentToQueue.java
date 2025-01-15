package luiz.sales.manager.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditSentToQueue implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String cpf;
	private String name;
	private String role;
	private LocalDateTime Date;
}
