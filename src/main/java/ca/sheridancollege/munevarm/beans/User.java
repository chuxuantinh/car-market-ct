package ca.sheridancollege.munevarm.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class User implements Serializable{
	
	private Long userID;
	@NonNull
	private String username;
	@NonNull
	private String encryptedPassword;
	@NonNull
	private Boolean enabled;
}
