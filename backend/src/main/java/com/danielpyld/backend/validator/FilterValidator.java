package com.danielpyld.backend.validator;

import com.danielpyld.backend.dto.CriteriaDto;
import com.danielpyld.backend.dto.FilterDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.danielpyld.backend.validator.ValidatorMessage.AT_LEAST_ONE_CRITERIA;
import static com.danielpyld.backend.validator.ValidatorMessage.MANDATORY_MESSAGE;

@Component
public class FilterValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterDto.class.equals(clazz);  // Validates only FilterDto objects
    }

    @Override
    public void validate(Object target, Errors errors) {
        FilterDto filter = (FilterDto) target;

        // Validate that the filter has a name
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", MANDATORY_MESSAGE);

        // Validate that the filter has at least one criterion and all criteria have valid fields
        if (filter.getCriterias() == null || filter.getCriterias().isEmpty() ||
                filter.getCriterias().stream().anyMatch(x -> x.getCriteriaType() == null || x.getCondition() == null || x.getValue() == null)) {
            errors.rejectValue("criterias", MANDATORY_MESSAGE);
        }

        // Ensure at least one criteria is selected
        if (filter.getCriterias().stream().noneMatch(CriteriaDto::getIsSelected)) {
            errors.rejectValue("criterias", AT_LEAST_ONE_CRITERIA);
        }
    }
}
