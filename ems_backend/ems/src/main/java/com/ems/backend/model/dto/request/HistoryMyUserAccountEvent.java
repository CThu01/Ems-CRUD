package com.ems.backend.model.dto.request;

import com.ems.backend.model.entity.MyUserAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryMyUserAccountEvent {

	private MyUserAccount myUserAccount;
	private String email;
}
