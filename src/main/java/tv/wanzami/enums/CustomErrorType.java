package tv.wanzami.enums;

import graphql.ErrorClassification;

public enum CustomErrorType implements ErrorClassification {
	RESOURCE_NOT_FOUND, INVALID_OPERATION,UNAUTHORIZED;
}
