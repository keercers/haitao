package com.thinvent.basicpf.util;

import com.thinvent.basicpf.model.Company;
import com.thinvent.library.Constants;
import com.thinvent.library.dim.TvtExceptionEnum;
import com.thinvent.library.exception.OptionException;
import com.thinvent.library.exception.ThinventBaseException;

public class CompanyVerifyUtil {

	private CompanyVerifyUtil(){}

	public static void verifyUpdate(String state) throws ThinventBaseException{
		if(!Constants.RES_SUCCESS.equals(state)){
			throw new OptionException(TvtExceptionEnum.OPT_SAVE_FAULSE.getIndex(), TvtExceptionEnum.OPT_SAVE_FAULSE.getName());
		}
	}
	
	public static void verifyObject(Object object) throws ThinventBaseException{
		if(object == null){
			throw new OptionException(TvtExceptionEnum.OPT_QUERY_FAULSE.getIndex(), TvtExceptionEnum.OPT_QUERY_FAULSE.getName());
		}
	}

	public static void verifyDeleteComResult(Boolean existsDept) throws ThinventBaseException {
		if (existsDept) {
			throw new OptionException(TvtExceptionEnum.DATA_VER_COM_DEP_CHILD_EXSIT.getIndex(), TvtExceptionEnum.DATA_VER_COM_DEP_CHILD_EXSIT.getName());
		}
	}

	public static void verifySaveCompany(Boolean exist) throws ThinventBaseException {
		if (exist) {
			throw new OptionException(TvtExceptionEnum.DATA_VER_COM_COM_CHILD_EXSIT.getIndex(), TvtExceptionEnum.DATA_VER_COM_COM_CHILD_EXSIT.getName());
		}
	}

	public static void verifyAddExistComName(Company company) throws ThinventBaseException {
		if (company != null) {
			throw new OptionException(TvtExceptionEnum.DATA_VER_COM_COMNAME_EXSIT.getIndex(),TvtExceptionEnum.DATA_VER_COM_COMNAME_EXSIT.getName());
		}
	}

	public static void verifyUpdateExistComName(Company company,Company oldCompany) throws ThinventBaseException {
		if (company != null && !company.getComId().equals(oldCompany.getComId())) {
			throw new OptionException(TvtExceptionEnum.DATA_VER_COM_COMNAME_EXSIT.getIndex(),TvtExceptionEnum.DATA_VER_COM_COMNAME_EXSIT.getName());
		}
	}


	public static void verifyDelExistComChild(Company company) throws ThinventBaseException {
		if (company != null) {
			throw new OptionException(TvtExceptionEnum.DATA_VER_COM_COMPANY_CHILD_EXSIT.getIndex(),TvtExceptionEnum.DATA_VER_COM_COMPANY_CHILD_EXSIT.getName());
		}
	}
}
