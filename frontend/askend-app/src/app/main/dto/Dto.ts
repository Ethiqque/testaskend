export interface FilterDTO {
    id: any;
    name: string;
    criterias: CriteriaDTO[];
    createdAt: any;
}

export interface CriteriaDTO {
    id: any;
    criteriaType: any;
    condition: any;
    value: any;
    isSelected: any;
}
