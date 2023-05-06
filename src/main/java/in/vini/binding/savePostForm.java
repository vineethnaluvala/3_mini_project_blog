package in.vini.binding;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
public class savePostForm {

	private Integer id;
	private String title;
	private String description;
	private String content;
	
	private LocalDate createdDate;
}
