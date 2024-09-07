package org.example.fxdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemAction {
    private boolean isEditAllowed;
    private boolean isDeleteAllowed;
}
