package com.ebay.epic.sojourner.flink.connectors.kafka.config;

import com.ebay.epic.sojourner.common.constant.SessionType;
import com.ebay.epic.sojourner.utils.DataCenter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

import static com.ebay.epic.sojourner.common.env.FlinkEnvUtils.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ConfigManager {
    private DataCenter dataCenter = DataCenter.RNO;
    private boolean isDrived = false;
    public static final String DEL_POINT = ".";
    public static final String DEL_SPACE = " ";
    public static final String DEL_LINE = "-";

    public String getOPName(String baseName) {
        return getString(baseName);
    }

    public String getOPUid(String baseName) {
        return getString(baseName);
    }

    public String getSlotSharingGroup(String baseName) {
        return getString(baseName);
    }

    public String getPath(String baseName,SessionType sessionType) {
        return getString(String.join(DEL_POINT,baseName,sessionType.name().toLowerCase()));
    }

    public String getBrokers(String baseName) {
        if(isDrived) {
            return getListString(getKeyName(baseName));
        }else{
            return getListString(baseName);
        }
    }

    public List<String> getTopics(String baseName) {
        return getList(baseName);
    }

    public int getParallelism(String baseName) {
        return getIntValue(baseName);
    }

    public String getStrValue(String baseName) {
        if (!isDrived) {
            return getString(baseName);
        } else {
            return getString(getKeyName(baseName));
        }
    }

    public String getStrValueNODC(String baseName) {
        if (!isDrived) {
            return getString(baseName);
        } else {
            return getString(getKeyNameNODC(baseName));
        }
    }

    public String getStrDirect(String key) {
        return getString(key);
    }

    public Integer getIntValue(String baseName) {
        return getInteger(baseName);
        //        if (!isDrived) {
        //            return getInteger(baseName);
        //        } else {
        //            return getInteger(getKeyName(baseName));
        //        }
    }

    private String getKeyName(String baseName) {
        return String.join(DEL_POINT, baseName,
                this.dataCenter.name().toLowerCase());
    }

    private String getKeyNameNODC(String baseName) {
        return baseName;
    }

}
