package com.ruoyi.mj.domain;

import lombok.Data;


@Data
public class SubmitChangeDTO {

	private String taskId;

	private String action;

	private Integer index;

	private String state;

	private String notifyHook;
}
