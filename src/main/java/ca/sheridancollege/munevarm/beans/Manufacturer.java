package ca.sheridancollege.munevarm.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manufacturer implements Serializable{
	private Long manufacturerID;
	private String manufacturerName;	
}
