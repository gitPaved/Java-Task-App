package com.task.interfaces;

import com.task.bean.TaskBean;


public interface OnPressTask {
    void execute(TaskBean bean, String action);
}

