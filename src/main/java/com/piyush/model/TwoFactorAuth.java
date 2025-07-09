package com.piyush.model;
import com.piyush.domain.VerificationType;

import lombok.Data;

@Data
public class TwoFactorAuth {
    private   boolean isEnabled = false;
    private VerificationType sendTo;

}
