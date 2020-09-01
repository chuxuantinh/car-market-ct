package ca.sheridancollege.munevarm.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car implements Serializable{
	
	private Long carID;
	private Long manufacturerID;
	private int year;
	private String carModel;
	private String color;
	private double price;
}
