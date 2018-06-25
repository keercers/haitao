package com.thinvent.basicpf.util;

import com.thinvent.basicpf.model.Department;
import com.thinvent.library.dim.TvtExceptionEnum;
import com.thinvent.library.exception.DataNotExistException;
import com.thinvent.library.exception.OptionException;
import com.thinvent.library.exception.ThinventBaseException;

public class DepartmentVerifyUtil {

    private DepartmentVerifyUtil() {
    }

    public static void verifyDept(Department department) throws ThinventBaseException {
        if (department == null)
        	throw new DataNotExistException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(), TvtExceptionEnum.DATA_NOTEXSIT.getName());
    }

    public static void verifyDeptName(Department existDepartment) throws ThinventBaseException {
        if (existDepartment != null) {
            throw new OptionException(TvtExceptionEnum.DATA_VER_DEP_DEPTNAME_EXSIT.getIndex(),TvtExceptionEnum.DATA_VER_DEP_DEPTNAME_EXSIT.getName());
        }
    }

    public static void verifyUpdateDept(Department existDepartment,Department department) throws ThinventBaseException {
        if (existDepartment != null && !existDepartment.getDepId().equals(department.getDepId()) ) {
            throw new OptionException(TvtExceptionEnum.DATA_VER_DEP_DEPTNAME_EXSIT.getIndex(),TvtExceptionEnum.DATA_VER_DEP_DEPTNAME_EXSIT.getName());
        }
    }

    public static void verifyDeleteDeptResult(Boolean existsDept,Boolean existsPos) throws ThinventBaseException {
        if (existsDept) {
            throw new OptionException(TvtExceptionEnum.DATA_VER_DEP_CHILD_EXSIT.getIndex(), TvtExceptionEnum.DATA_VER_DEP_CHILD_EXSIT.getName());
        }
        if (existsPos) {
            throw new OptionException(TvtExceptionEnum.DATA_VER_DEP_POS_CHILD_EXSIT.getIndex(), TvtExceptionEnum.DATA_VER_DEP_POS_CHILD_EXSIT.getName());
        }
    }
}
