<#assign array=[]><#list NoOfState1 as x><#assign array=array+[x.value]></#list><#assign i=0>
 {
 	<#assign InsuredLevels="InsuredLevel1"><#list InsuredLevels?eval as result>     
        "${result.atrib}":"${result.value}",
        </#list> 
 	"State": [
 	<#list 1..array[i] as x> 
    {
    <#assign States="State"+x><#list States?eval as result>     
        "${result.atrib}":"${result.value}",
        </#list> 
    "AddedPersonalInjuryProtection": [
        {
          "AddedPersonalInjuryProtectionDetail": {
            "FamilyMembersName": "1"
          }
        }
      ],
      "BusinessInterruptionCoverage": [
        {
          "BusinessIncomeCoverageType": "Business Income Without Extra Expense Coverage",
          "BusinessInterruptionCoverageDetail": [
            {
              "Limit": "500",
              "MaxAmtOfBusnsIncmLossAfterRepdOrRepld": "500",
              "MaximumAmountOfExtraExpense": "500",
              "MaxiumumAmountOfBusinessIncomeLoss": "500",
              "ScheduledPropertyOption": "Option A"
            }
          ],
          "DurationOfWaitingPeriod": "No waiting period",
          "NumberOfDays": "90"
        }
      ],
      "TrailerInterchangeAgreement": [
        {
          "TrailerInterchangeAgreementDetail": [
            {
              "TrailerInterchangeAgreementCollisionCoverageDeductible": "1,000",
              "TrailerInterchangeAgreementCollisionCoverageLegalLiability": "9999999",
              "TrailerInterchangeAgreementComprehensiveCoverageLegalLiability": "9999999",
              "TrailerInterchangeAgreementDetailGaragingAndTerminalZoneType": "Metropolitan To Metropolitan",
              "TrailerInterchangeAgreementDetailNumberOfDays": "15",
              "TrailerInterchangeAgreementDetailNumberOfTrailers": "13",
              "TrailerInterchangeAgreementDetailRadiusClass": "Intermediate",
              "TrailerInterchangeAgreementDetailZoneOfTerminal": "Pacific",
              "TrailerInterchangeAgreementFireAndTheftManualPremium": "150.0",
              "TrailerInterchangeAgreementFireManualPremium": "150.0",
              "TrailerInterchangeAgreementSpecifiedCausesOfLossCoverageLegalLiability": "9999999"
            }
          ]
        }
      ],
      "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividuals": [
        {
          "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsDetail": [
            {
              "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsCollisionCoverageDeductible": "1,000",
              "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsLiabilityCoverageCoverageType": "",
              "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsLiabilityCoverageDeductible": "250 Combined Single Limit",
              "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsLiabilityCoverageFinancialResponsibilityRequirement": "",
              "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsLiabilityCoverageLimitText": "3,000,000",
              "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsLiabilityCoveragePropertyDamageLimitText": "",
              "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsMedicalPaymentsCoverageLimitText": "500",
              "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsOtherThanCollisionCoverageDeductible": "1,000",
              "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsOtherThanCollisionCoverageFullGlassCoverage": "No",
              "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsUnderinsuredMotoristsCoverageCombinedSingleLimitText": "100,000",
              "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsUnderinsuredMotoristsCoverageCoverageType": "Combined Single Limit",
              "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsUnderinsuredMotoristsCoverageSplitLimit": "",
              "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsUnderinsuredMotoristsPropertyDamageCoverageLimitText": "100,000",
              "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsUninsuredMotoristsCoverageCombinedSingleLimitText": "100,000",
              "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsUninsuredMotoristsCoverageCoverageType": "Combined Single Limit",
              "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsUninsuredMotoristsCoverageSplitLimit": "",
              "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsUninsuredMotoristsPropertyDamageCoverageDeductible": "Full Coverage",
              "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsUninsuredMotoristsPropertyDamageCoverageLimitText": "100,000"
            }
          ],
          "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsSubjectToNoFault": "Yes"
        }
      ],
      "CommercialAutoPennsylvaniaNamedIndividualsBroadenedFirstPartyBenefits": [
        {
          "CommercialAutoPennsylvaniaNamedIndividualsBroadenedFirstPartyBenefitsDetail": {
            "AccidentalDeathBenefit": "",
            "CombinationFirstPartyBenefit": "",
            "CoverageType": "",
            "FuneralExpenseBenefit": "",
            "MedicalExpenseBenefit": "",
            "WorkLossBenefit": ""
          }
        }
      ],
      "CommercialAutoGaragekeepersCoverage": [
        {
          "CommercialAutoGaragekeepersCovCollisionCoverageDeductible": "100",
          "CommercialAutoGaragekeepersCovCollisionCoverageLimit": "6000",
          "CommercialAutoGaragekeepersCovCollisionCoverageRatingBase": "Legal Liability",
          "CommercialAutoGaragekeepersCovOtherThanCollisionCoverageDeductible": "100 / 500",
          "CommercialAutoGaragekeepersCovOtherThanCollisionCoverageLimit": "6000",
          "CommercialAutoGaragekeepersCovOtherThanCollisionCoverageRatingBase": "Legal Liability"
        }
      ],
      "CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipment": [
        {
          "CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipmentCoverageRatingBase": "Legal Liability",
          "CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipmentDetail": [
            {
              "CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipmentCollisionCoverageDeductible": "100",
              "CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipmentCollisionCoverageLimit": "7500",
              "CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipmentOtherThanCollisionCoverageDeductible": "100 / 500",
              "CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipmentOtherThanCollisionCoverageLimit": "7500"
            }
          ]
        }
      ],
      "Truck": [
        {
          "TruckDetail": {
            
            "AudioVisualAndDataElectronicEquipmentCoverageList": [
              {
                "AudioVisualAndDataElectronicEquipmentCoverageLimit": "500"
              }
            ],
            "CommercialAutoAdditionalPersonalInjuryProtectionNewYorkList": [
              {
                "AdditionalPersonalInjuryProtectionNewYorkAddedCoverage_AdditionalMonthlyWorkLoss": "Not Applicable",
                "AdditionalPersonalInjuryProtectionNewYorkAddedCoverage_CoverageType": "Not Applicable",
                "AdditionalPersonalInjuryProtectionNewYorkAddedCoverage_OtherExpensesLimit": "Not Applicable",
                "AdditionalPersonalInjuryProtectionNewYorkAddedCoverage_TotalAdditionalLimits": "Not Applicable",
                "AdditionalPersonalInjuryProtectionNewYorkDeathBenefitCoverage_AdditionalDeathBenefit": "Not Applicable",
                "AdditionalPersonalInjuryProtectionNewYorkDeathBenefitCoverage_Limit": "Not Applicable"
              }
            ],

            "CommercialAutoRentalReimbursementCovList": [
              {
                "DailyRentalAmount": "55",
                "MaximumRentalDays": "45"
              }
            ],
            <#assign TruckLevels="TruckLevel"+x><#list TruckLevels?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list> 
            }
        }
      ],
      
      "PrivatePassenger": [
        {
          "PrivatePassengerDetail": {
          "AudioVisualAndDataElectronicEquipmentCoverageList": [
              {
                "AudioVisualAndDataElectronicEquipmentCoverageLimit": "500"
              }
            ],
            "CommercialAutoAdditionalPersonalInjuryProtectionNewYorkList": [
              {
                "AdditionalPersonalInjuryProtectionNewYorkAddedCoverage_AdditionalMonthlyWorkLoss": "Not Applicable",
                "AdditionalPersonalInjuryProtectionNewYorkAddedCoverage_CoverageType": "Not Applicable",
                "AdditionalPersonalInjuryProtectionNewYorkAddedCoverage_OtherExpensesLimit": "Not Applicable",
                "AdditionalPersonalInjuryProtectionNewYorkAddedCoverage_TotalAdditionalLimits": "Not Applicable",
                "AdditionalPersonalInjuryProtectionNewYorkDeathBenefitCoverage_AdditionalDeathBenefit": "Not Applicable",
                "AdditionalPersonalInjuryProtectionNewYorkDeathBenefitCoverage_Limit": "Not Applicable"
              }
            ],
            "CommercialAutoRentalReimbursementCovList": [
              {
                "DailyRentalAmount": "55",
                "MaximumRentalDays": "45"
              }
            ],
            <#assign PrivatePassengerLevels="PrivatePassengerLevel"+x><#list PrivatePassengerLevels?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>  
            }
        }
      ],
      "PublicTransportation": [
        {
          "PublicTransportationDetail": {
            "AudioVisualAndDataElectronicEquipmentCoverageList": [
              {
                "AudioVisualAndDataElectronicEquipmentCoverageLimit": "500"
              }
            ],
            "CommercialAutoAdditionalPersonalInjuryProtectionNewYorkList": [
              {
                "AdditionalPersonalInjuryProtectionNewYorkAddedCoverage_AdditionalMonthlyWorkLoss": "Not Applicable",
                "AdditionalPersonalInjuryProtectionNewYorkAddedCoverage_CoverageType": "Not Applicable",
                "AdditionalPersonalInjuryProtectionNewYorkAddedCoverage_OtherExpensesLimit": "Not Applicable",
                "AdditionalPersonalInjuryProtectionNewYorkAddedCoverage_TotalAdditionalLimits": "Not Applicable",
                "AdditionalPersonalInjuryProtectionNewYorkDeathBenefitCoverage_AdditionalDeathBenefit": "Not Applicable",
                "AdditionalPersonalInjuryProtectionNewYorkDeathBenefitCoverage_Limit": "Not Applicable"
              }
            ],
            "CommercialAutoRentalReimbursementCovList": [
              {
                "DailyRentalAmount": "55",
                "MaximumRentalDays": "45"
              }
            ],
            <#assign PublicTransportationLevels="PublicTransportationLevel"+x><#list PublicTransportationLevels?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>    
             }
        }
      ],
      "ZoneRated": [
        {
          "ZoneRatedDetail": {
          "AudioVisualAndDataElectronicEquipmentCoverageList": [
              {
                "AudioVisualAndDataElectronicEquipmentCoverageLimit": "500"
              }
            ],
            "CommercialAutoAdditionalPersonalInjuryProtectionNewYorkList": [
              {
                "AdditionalPersonalInjuryProtectionNewYorkAddedCoverage_AdditionalMonthlyWorkLoss": "Not Applicable",
                "AdditionalPersonalInjuryProtectionNewYorkAddedCoverage_CoverageType": "Not Applicable",
                "AdditionalPersonalInjuryProtectionNewYorkAddedCoverage_OtherExpensesLimit": "Not Applicable",
                "AdditionalPersonalInjuryProtectionNewYorkAddedCoverage_TotalAdditionalLimits": "Not Applicable",
                "AdditionalPersonalInjuryProtectionNewYorkDeathBenefitCoverage_AdditionalDeathBenefit": "Not Applicable",
                "AdditionalPersonalInjuryProtectionNewYorkDeathBenefitCoverage_Limit": "Not Applicable"
              }
            ],
            "CommercialAutoRentalReimbursementCovList": [
              {
                "DailyRentalAmount": "55",
                "MaximumRentalDays": "45"
              }
            ],
            <#assign ZoneRatedLevels="ZoneRatedLevel"+x><#list ZoneRatedLevels?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>   
            }
        }
      ],
      "Special": [
        {
          "SpecialDetail": {
          "AudioVisualAndDataElectronicEquipmentCoverageList": [
              {
                "AudioVisualAndDataElectronicEquipmentCoverageLimit": "500"
              }
            ],
            "CommercialAutoAdditionalPersonalInjuryProtectionNewYorkList": [
              {
                "AdditionalPersonalInjuryProtectionNewYorkAddedCoverage_AdditionalMonthlyWorkLoss": "Not Applicable",
                "AdditionalPersonalInjuryProtectionNewYorkAddedCoverage_CoverageType": "Not Applicable",
                "AdditionalPersonalInjuryProtectionNewYorkAddedCoverage_OtherExpensesLimit": "Not Applicable",
                "AdditionalPersonalInjuryProtectionNewYorkAddedCoverage_TotalAdditionalLimits": "Not Applicable",
                "AdditionalPersonalInjuryProtectionNewYorkDeathBenefitCoverage_AdditionalDeathBenefit": "Not Applicable",
                "AdditionalPersonalInjuryProtectionNewYorkDeathBenefitCoverage_Limit": "Not Applicable"
              }
            ],
            "CommercialAutoRentalReimbursementCovList": [
              {
                "DailyRentalAmount": "55",
                "MaximumRentalDays": "45"
              }
            ],
            <#assign SpecialTypesLevels="SpecialTypesLevel"+x><#list SpecialTypesLevels?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>   
            }
        }
      ],
      "HiredAuto": [
        {
          "HiredAutoDetail": {
          	<#assign HiredAutoLevels="HiredAutoLevel"+x><#list HiredAutoLevels?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>   
          }
        }
      ],
      "NonOwnedAuto": [
        {
          "NonOwnedAutoDetail": {
          	<#assign NonOwnedAutoLevels="NonOwnedAutoLevel"+x><#list NonOwnedAutoLevels?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>    
          }
        }
      ]
    }<#if x?is_last><#else>,</#if>
    </#list>  
  ],
  "OwnerId": "35",
  "ServiceRequestDetail": {
    <#assign ServiceDetailLevels="ServiceDetailLevel1"><#list ServiceDetailLevels?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
  }
}