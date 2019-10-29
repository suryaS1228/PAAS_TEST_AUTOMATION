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
              <#assign TrailerInterchanges="TrailerInterchange"+x><#list TrailerInterchanges?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list> 
            }
          ]
        }
      ],
      "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverage": [
        {
          "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageDetail": [
            {
              <#assign NamedIndividualss="NamedIndividuals"+x><#list NamedIndividualss?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
        	}
          ]
        }
      ],
      "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividuals": [
        {
          "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsDetail": [
            {
              <#assign DriveOtherCars="DriveOtherCar"+x><#list DriveOtherCars?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list> 
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
            "LocationNumber": "",
            "MedicalExpenseBenefit": "",
            "WorkLossBenefit": ""
          }
        }
      ],
      "Garage": [
                {
                    "GarageDetail": {
                        "AddressLine1": "8TH ST",
                        "AddressLine2": "PHOENIX",
                        "City": "Waldorf",
                        "County": "Surry",
                        "GarageClassCode": "7361",
                        "GarageCoverageType": "Full Covered Autos Liability Limit For Customers Coverage",
                        "GarageNumberOfOfficersAndOperatorsFullTime": "5",
                        "GarageNumberOfOfficersAndOperatorsPartTime": "10",
                        "GarageNumberOfOtherEmployeesFullTime": "1",
                        "GarageNumberOfOtherEmployeesPartTime": "2",
                        "NoOfVehicles": "1",
                        "State": "RI",
                        "Territory": "117",
                        "VehicleType": "Garage",
                        "ZipCode": "85001"
                    }
                }
            ],
      "CommercialAutoGaragekeepersCoverage": [
        {
          "CommercialAutoGaragekeepersCovOtherThanCollisionCoverageAllPerilsDeductible": "Yes",
          "CommercialAutoGaragekeepersCovOtherThanCollisionCoverageCoverageType": "Comprehensive",
          "CommercialAutoGaragekeepersCovRatingBase": "Direct (Excess)",
          "CommercialAutoGaragekeepersCovCollisionCoverageDeductible": "100",
          "CommercialAutoGaragekeepersCovCollisionCoverageLimit": "6000",
          "CommercialAutoGaragekeepersCovOtherThanCollisionCoverageDeductible": "100 / 500",
          "CommercialAutoGaragekeepersCovOtherThanCollisionCoverageLimit": "6000"
          
        }
      ],
      "CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipment": [
        {
          "CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipmentCoverageRatingBase": "Legal Liability",
          "CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipmentDetail": [
            {
              "CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipmentCollisionCoverageManualPremium": "200",
              "CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipmentOtherThanCollisionCoverageManualPremium": "600",
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
            "LocationNumber": "1",
            "TruckFirstOrAdditionalAuto": "Additional Auto",
             "VehicleNumber": "1",
            "AudioVisualAndDataElectronicEquipmentCoverageList": [
              {
                "AudioVisualAndDataElectronicEquipmentCoverageLimit": "500",
                "CostNew": "500"
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
           "LocationNumber": "1",
           "PrivatePassengerFirstOrAdditionalAuto": "Additional Auto",
           "VehicleNumber": "1",
          "AudioVisualAndDataElectronicEquipmentCoverageList": [
              {
                "AudioVisualAndDataElectronicEquipmentCoverageLimit": "500",
                "CostNew": "500"
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
           "LocationNumber": "1",
            "PublicTransportationFirstOrAdditionalAuto": "Additional Auto",
            "VehicleNumber": "1",
            "AudioVisualAndDataElectronicEquipmentCoverageList": [
              {
                "AudioVisualAndDataElectronicEquipmentCoverageLimit": "500",
                "CostNew": "500"
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
          "LocationNumber": "1",
          "VehicleNumber": "1",
          "ZoneRatedFirstOrAdditionalAuto": "Additional Auto",
          "AudioVisualAndDataElectronicEquipmentCoverageList": [
              {
                "AudioVisualAndDataElectronicEquipmentCoverageLimit": "500",
                "CostNew": "500"
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
          "LocationNumber": "1",
          "SpecialTypesFirstOrAdditionalAuto": "Additional Auto",
          "SpecialTypesLiabilityCoverageTortLimitation": "",
          "AudioVisualAndDataElectronicEquipmentCoverageList": [
              {
                "AudioVisualAndDataElectronicEquipmentCoverageLimit": "500",
                "CostNew": "500"
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
          "LocationNumber": "1",
          	<#assign HiredAutoLevels="HiredAutoLevel"+x><#list HiredAutoLevels?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>   
          }
        }
      ],
      "NonOwnedAuto": [
        {
          "NonOwnedAutoDetail": {
            "LocationNumber": "1",
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