<#assign i=0>
<#assign array=[]><#list NoOfState1 as x><#assign array=array+[x.value]></#list>
{
	<#-- <#assign InsuredLevels="InsuredLevel1"><#list InsuredLevels?eval as result>     
        "${result.atrib}":"${result.value}",
        </#list> -->
     <#assign policyDetails="policy1"><#list policyDetails?eval as result>     
        "${result.atrib}":"${result.value}",
        </#list>
        
	  "ServiceRequestDetail": {
	    "BrowserIp": "111.93.119.74",
	    "OwnerId": "35",
	    "ResponseType": "JSON",
	    "ServiceRequestVersion": "1.0",
	    <#--  "Token": "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",-->
	    "Token": "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",
	    "UserName": "StarrCAAgent"
	  },
	  "Forms": {
    "FormDetail": [
     
      {
        "FormName": "PREFERRED GUIDELINES FOR INSURED DRIVERS",
        "FormNumber": "SI GUIDELINES 0519",
        "FormType": "Mandatory",
        "Sequence": "3"
      }
    ]
  },
	"ScheduleOfFormsList": {
    "ScheduleOfFormsDetail": [
     
      {
        "FormName": "Business Auto Symbol",
        "Sequence": "40000"
      }
    ]
  },
	"State": [
	<#list 1..array[i] as x> 
	<#assign j=0>
	<#assign policylevels="Policylevel"+x>				<#assign policylevelarray=[]>		<#list policylevels?eval as y>		<#assign policylevelarray=policylevelarray+[y.value]>				</#list>
	<#assign Trucklevels="Trucklevel"+x>				<#assign Trucklevelarray=[]>		<#list Trucklevels?eval as y>		<#assign Trucklevelarray=Trucklevelarray+[y.value]>					</#list>
	<#assign Pptlevels="Pptlevel"+x>					<#assign Pptlevelarray=[]>			<#list Pptlevels?eval as y>			<#assign Pptlevelarray=Pptlevelarray+[y.value]>						</#list>
	<#assign PublicTranslevels="PublicTranslevel"+x>	<#assign PublicTranslevelarray=[]>	<#list PublicTranslevels?eval as y>	<#assign PublicTranslevelarray=PublicTranslevelarray+[y.value]>		</#list>
	<#assign ZoneRatedlevels="ZoneRatedlevel"+x>		<#assign ZoneRatedlevelarray=[]>	<#list ZoneRatedlevels?eval as y>	<#assign ZoneRatedlevelarray=ZoneRatedlevelarray+[y.value]>			</#list>
	<#assign SpecialTypeslevels="SpecialTypeslevel"+x>	<#assign SpecialTypeslevelarray=[]>	<#list SpecialTypeslevels?eval as y><#assign SpecialTypeslevelarray=SpecialTypeslevelarray+[y.value]>	</#list>
	<#assign Garagelevels="Garagelevel"+x>				<#assign Garagelevelarray=[]>		<#list Garagelevels?eval as y>		<#assign Garagelevelarray=Garagelevelarray+[y.value]>				</#list>
    
    {
    <#assign States="State"+x><#list States?eval as result>     
        "${result.atrib}":"${result.value}",
        </#list> 
    
      "Forms": {
      "FormDetail": [
                               
      <#assign j=j+1>
        <#list 1..policylevelarray[0] as xx>
        {
        	<#assign PolicyForms="PolicyForm"+j+x><#list PolicyForms?eval as result>         	    
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list> 
        }<#if xx?is_last><#else>,</#if><#assign j=j+1>
	    </#list>        
      ]
      },
      
    <#--    "CommercialAutoTruckersExcessCoverageForNamedInsuredAndNamedLessorsForLeasedAutoList": [
        {
          "CommercialAutoTruckersExcessCoverageForNamedInsuredAndNamedLessorsForLeasedAutoDetail": {
            <#assign TruckersExcessCoverageForNamedInsuredAndNamedLessorsForLeasedAutoDetails="TruckersExcessCoverageForNamedInsuredAndNamedLessorsForLeasedAutoDetail"+x><#list TruckersExcessCoverageForNamedInsuredAndNamedLessorsForLeasedAutoDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
          }
        }
      ],-->
      
      "CommercialAutoLeasingOrRentalConcernsContingentCoverageList": [
                {
                <#assign CommercialAutoLeasingOrRentalConcernsContingentCoverageLists="CommercialAutoLeasingOrRentalConcernsContingentCoverageList"+x><#list CommercialAutoLeasingOrRentalConcernsContingentCoverageLists ?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
                <#--      "CalculatedPremium": "0.0",
                    "Factor": "0.050",
                    "LesseeLiabilityPremium": "5",
                    "LiabilityLimitText": "0",
                    "Premium": "0.00",
                    "TerrorismPhysicalDamagePremium": "",
                    "TotalPhysicalDamagePremiumForTerrorismCoverage": "",
                    "TotalTerrorismPremium": ""-->
                }
            ],
            "Location": [
                {
                    "LiabilityCompositeRate": "26.0",
                    "LiabilityExposure": "1",
                    "LiabilityPremium": "26.0",
                    "LocationNumber": "1",
                    "PerTruck": [
                        {
                            "LiabilityCompositeRate": "988.0000",
                            "LiabilityDescription": "Light Truck",
                            "LiabilityExposure": "1",
                            "LiabilityPremium": "988.00",
                            "SizeClass": "Light Truck"
                        }
                    ],
                    "PhysicalDamageCompositeRate": "353.0",
                    "PhysicalDamageExposure": "1",
                    "PhysicalDamagePremium": "353.0"
                },
                {
                    "CostNew": [
                        {
                            "MaximumRange": "244556770",
                            "MinimumRange": "234556770",
                            "PhysicalDamageCompositeRate": "0.0000",
                            "PhysicalDamageDescription": "Cumulative CostNew for all vehicles in Range $234556770 to $244556770",
                            "PhysicalDamageExposure": "0",
                            "PhysicalDamagePremium": "0.00"
                        },
                        {
                            "MaximumRange": "23500001",
                            "MinimumRange": "22250001",
                            "PhysicalDamageCompositeRate": "0.0000",
                            "PhysicalDamageDescription": "Cumulative CostNew for all vehicles in Range $22250001 to $23500001",
                            "PhysicalDamageExposure": "0",
                            "PhysicalDamagePremium": "0.00"
                        }
                    ],
                    "LiabilityCompositeRate": "20.0",
                    "LiabilityExposure": "1",
                    "LiabilityPremium": "20.0",
                    "LocationNumber": "2",
                    "PhysicalDamageCompositeRate": "367.0",
                    "PhysicalDamageExposure": "1",
                    "PhysicalDamagePremium": "367.0"
                },
                {
                    "LiabilityCompositeRate": "157.0",
                    "LiabilityExposure": "1",
                    "LiabilityPremium": "157.0",
                    "LocationNumber": "3",
                    "PhysicalDamageCompositeRate": "597.0",
                    "PhysicalDamageExposure": "1",
                    "PhysicalDamagePremium": "597.0"
                },
                {
                    "CostNew": [
                        {
                            "MaximumRange": "50000",
                            "MinimumRange": "100000",
                            "PhysicalDamageCompositeRate": "0.0000",
                            "PhysicalDamageDescription": "Cumulative CostNew for all vehicles in Range $100000 to $50000",
                            "PhysicalDamageExposure": "0",
                            "PhysicalDamagePremium": "0.00"
                        },
                        {
                            "MaximumRange": "150000",
                            "MinimumRange": "250001",
                            "PhysicalDamageCompositeRate": "0.0000",
                            "PhysicalDamageDescription": "Cumulative CostNew for all vehicles in Range $250001 to $150000",
                            "PhysicalDamageExposure": "0",
                            "PhysicalDamagePremium": "0.00"
                        }
                    ],
                    "LiabilityCompositeRate": "778.0",
                    "LiabilityExposure": "2",
                    "LiabilityPremium": "1556.0",
                    "LocationNumber": "4",
                    "PerTruck": [
                        {
                            "LiabilityCompositeRate": "568.0000",
                            "LiabilityDescription": "Medium Truck",
                            "LiabilityExposure": "1",
                            "LiabilityPremium": "568.00",
                            "SizeClass": "Medium Truck"
                        }
                    ],
                    "PhysicalDamageCompositeRate": "746.0",
                    "PhysicalDamageExposure": "2",
                    "PhysicalDamagePremium": "1492.0"
                }
            ],
    
     "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverage": [
                {
                    "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageDetail": [
                        {
                        
                         <#assign NamedIndividualsBroadenedPersonalInjuryProtectionCoverages="NamedIndividualsBroadenedPersonalInjuryProtectionCoverage"+x><#list NamedIndividualsBroadenedPersonalInjuryProtectionCoverages ?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
                         <#--    "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageAddedPIPPremium": "",
                            "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageAddedPIPRate": "",
                            "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageBasePremium": "",
                            "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageBroadendedRate": "",
                            "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageBroadenedPremium": "",
                            "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageCalculatedPolicyTermPremium": "",
                            "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageDetailPremium": "",
                            "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageExtendedBenefitsFactor": "",
                            "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageExtendedPremium": "",
                            "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageExtendedRate": "",
                            "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoveragePolicyTermPremium": "",
                            "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoveragePreliminaryBasePremium": "",
                            "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageRate": "",
                            "NamedIndividualsBroadenedPersonalInjuryProtectionCoverageAccidentPreventionDiscount": "Yes",
                            "NamedIndividualsBroadenedPersonalInjuryProtectionCoverageAccidentPreventionDiscountFactor": "",
                            "NamedIndividualsBroadenedPersonalInjuryProtectionCoverageAddedPIPCoverage": "Yes",
                            "NamedIndividualsBroadenedPersonalInjuryProtectionCoveragePIPExtendedBenefits": "Yes",
                            "NamedIndividualsBroadenedPersonalInjuryProtectionCoverageRatingBasis": "Covered By Workers Comp and Principally Operated by Employees",
                            "Territory": "",
                            "TerrorismLiabilityPremium": "",
                            "TerrorismPhysicalDamagePremium": "",
                            "TotalLiabilityPremiumForTerrorismCoverage": "",
                            "TotalPhysicalDamagePremiumForTerrorismCoverage": "",
                            "TotalTerrorismPremium": "",
                            "ZipCode": ""--> 
                        }
                    ],
                    "LocationNumber": "1"
                }
            ], 
      "CommercialAutoExperienceRatingModificationList": [
        {
           <#assign CommercialAutoExperienceRatingModificationDetails="CommercialAutoExperienceRatingModificationDetail"+x><#list CommercialAutoExperienceRatingModificationDetails ?eval as result>     
        "${result.atrib}":"${result.value}",
        </#list>
          "CommercialAutoExperienceRatingModificationYear1DetailList": [
            {
              <#assign CommercialAutoExperienceRatingModificationYear1Details="CommercialAutoExperienceRatingModificationYear1Detail"+x><#list CommercialAutoExperienceRatingModificationYear1Details?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
            }
          ],
          "CommercialAutoExperienceRatingModificationYear2DetailList": [
            {
              <#assign CommercialAutoExperienceRatingModificationYear2Details="CommercialAutoExperienceRatingModificationYear2Detail"+x><#list CommercialAutoExperienceRatingModificationYear2Details?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
            }
          ],
          "CommercialAutoExperienceRatingModificationYear3DetailList": [
            {
             <#assign CommercialAutoExperienceRatingModificationYear3Details="CommercialAutoExperienceRatingModificationYear3Detail"+x><#list CommercialAutoExperienceRatingModificationYear3Details?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
            }
          ]
        }
      ],
      
     
      "CommercialAutoScheduleRatingModificationList": [
        {
          <#assign CommercialAutoScheduleRatingModificationDetails="CommercialAutoScheduleRatingModificationDetail"+x><#list CommercialAutoScheduleRatingModificationDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
        }
      ],
      
      "CommercialAutoPennsylvaniaNamedIndividualsBroadenedFirstPartyBenefits": [
        {
          "CommercialAutoPennsylvaniaNamedIndividualsBroadenedFirstPartyBenefitsDetail": {
            <#assign CommercialAutoPennsylvaniaNamedIndividualsBroadenedFirstPartyBenefitsDetails="CommercialAutoPennsylvaniaNamedIndividualsBroadenedFirstPartyBenefitsDetail"+x><#list CommercialAutoPennsylvaniaNamedIndividualsBroadenedFirstPartyBenefitsDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
          }
        }
      ],
      "AdditionalInsuredList": [],
      
      "BusinessInterruptionCoverage": [
        {
          <#assign BusinessInterruptionCoverageDetails="BusinessInterruptionCoverageDetail"+x><#list BusinessInterruptionCoverageDetails?eval as result>     
        "${result.atrib}":"${result.value}",
        </#list>
         
          "BusinessInterruptionCoverageDetail": [
            {
              <#assign BusinessInterruptionCoverageDetDetails="BusinessInterruptionCoverageDetDetail"+x><#list BusinessInterruptionCoverageDetDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
              
            }
          ]
          
        }
      ],
      "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividuals": [
        {
          <#assign CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualssDetails="CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsList"+x><#list CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualssDetails?eval as result>     
        "${result.atrib}":"${result.value}",
        </#list>
          "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsDetail": [
            {
              <#assign CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsDetails="CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsDetail"+x><#list CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
            }
          ]
        }
      ],
      "CommercialAutoGaragekeepersCoverage": [
        {
          <#assign CommercialAutoGaragekeepersCoverageDetails="CommercialAutoGaragekeepersCoverageDetail"+x><#list CommercialAutoGaragekeepersCoverageDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
        }
      ],
      "CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipment": [
        {
          <#assign CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipmentsDetails="CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipmentList"+x><#list CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipmentsDetails?eval as result>     
        "${result.atrib}":"${result.value}",
        </#list>
          "CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipmentDetail": [
            {
              <#assign CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipmentDetails="CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipmentDetail"+x><#list CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipmentDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
            }
          ]
        }
      ],
      
   <#--    "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverage": [
        {
          "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageDetail": [
            {
              <#assign CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageDetails="CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageDetail"+x><#list CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
            }
          ],
          "LocationNumber": "1"
        }
      ],-->
     
      "CommercialAutoPolicyRentalReimbursementCoverageList": [
        {
          <#assign CommercialAutoPolicyRentalReimbursementCoverageDetails="CommercialAutoPolicyRentalReimbursementCoverageDetail"+x><#list CommercialAutoPolicyRentalReimbursementCoverageDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
        }
      ],
      
     <#--  "CommercialAutoOfficialInspectionStations": [
        {
          "CommercialAutoOfficialInspectionStationsDetail": {
            <#assign OfficialInspectionStationsDetails="OfficialInspectionStationsDetail"+x><#list OfficialInspectionStationsDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
          }
        }
      ],
      "CommercialAutoCancellationNonrenewalNoticeToDesignatedPersonOrganizationList": [
        {
          "CommercialAutoCancellationNonrenewalNoticeToDesignatedPersonOrganizationDetail": {
            <#assign CancellationNonrenewalNoticeToDesignatedPersonOrganizationDetails="CancellationNonrenewalNoticeToDesignatedPersonOrganizationDetail"+x><#list CancellationNonrenewalNoticeToDesignatedPersonOrganizationDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
          }
        }
      ],
      
    
      
      "CommercialAutoFellowEmployeeCoverageForDesignatedEmployeesPositionsList": [
        {
          "CommercialAutoFellowEmployeeCoverageForDesignatedEmployeesPositionsDetail": {
           <#assign FellowEmployeeCoverageForDesignatedEmployeesPositionsDetails="FellowEmployeeCoverageForDesignatedEmployeesPositionsDetail"+x><#list FellowEmployeeCoverageForDesignatedEmployeesPositionsDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
          }
        }
      ],--->
      "CommercialAutoTruckersExcessCoverageForNamedInsuredAndNamedLessorsForLeasedAutoList": [
        {
          "CommercialAutoTruckersExcessCoverageForNamedInsuredAndNamedLessorsForLeasedAutoDetail": {
            <#assign TruckersExcessCoverageForNamedInsureds="TruckersExcessCoverageForNamedInsured"+x><#list TruckersExcessCoverageForNamedInsureds?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
          }
        }
      ],
      "CommercialAutoWaiverOfTransferOfRightsOfRecoveryAgainstOthersToUsList": [
        {
          "CommercialAutoWaiverOfTransferOfRightsOfRecoveryAgainstOthersToUsDetail": {
            <#assign WaiverOfTransferOfRights ="WaiverOfTransferOfRight"+x><#list WaiverOfTransferOfRights?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
          }
        }
      ],
       "CommercialAutoTruckersNamedLesseeAsInsuredList": [
        {
          "CommercialAutoTruckersNamedLesseeAsInsuredDetail": {
           <#assign TruckersNamedLesseeAsInsureds="TruckersNamedLesseeAsInsured"+x><#list TruckersNamedLesseeAsInsureds?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
          
          }
        }
      ],
      "CommercialAutoDesignatedInsuredList": [
        {
          "CommercialAutoDesignatedInsuredDetail": {
           <#assign DesignatedInsureds="DesignatedInsured"+x><#list DesignatedInsureds?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
            
          }
        }
      ],
      "CommercialAutoColoradoChangesDriverExclusionList": [
        {
          "CommercialAutoColoradoChangesDriverExclusionDetail": {
           <#assign ColoradoChangesDrivers="ColoradoChangesDriver"+x><#list ColoradoChangesDrivers?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
        
          }
        }
      ],
      "CommercialAutoAdditionalInsuredList": [
        {
          "CommercialAutoAdditionalInsuredDetail": {
           <#assign AdditionalInsureds="AdditionalInsured"+x><#list AdditionalInsureds?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
            
          }
        }
      ],
      
      "CommercialAutoCoverageForCertainOperationsInConnectionWithRailroadsList": [
        {
          "CommercialAutoCoverageForCertainOperationsInConnectionWithRailroadsDetail": {
           <#assign CoverageForCertainOperationsInConnections="CoverageForCertainOperationsInConnection"+x><#list CoverageForCertainOperationsInConnections?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
            
          }
        }
      ],
      "CommercialAutoNewYorkCoverageForCertainOperationsInConnectionWithRailroadsList": [
        {
          "CommercialAutoNewYorkCoverageForCertainOperationsInConnectionWithRailroadsDetail": {
           <#assign CoverageForCertainOperationsInConnectionsNY="CoverageForCertainOperationsInConnectionNY"+x><#list CoverageForCertainOperationsInConnectionsNY?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
          
          }
        }
      ],
       "AddedPersonalInjuryProtection": [
                {
                    "AddedPersonalInjuryProtectionDetail": {
                        "CommercialAutoAddedPersonalInjuryProtectionDetail.IncreaseLimitsOfMedicalExpenseBenefit": "",
                        "FamilyMembersName": "1"
                    },
                    "AddedPIPCoverageType": "",
                    "AddedPIPNamedInsureds": ""
                }
            ],
      "CommercialAutoPersonalInjuryProtectionCovList": [
        {
          "CommercialAutoAddedPersonalInjuryProtectionDetail": {
            "AdditionalChiropracticTreatments": "Sample_Data"
          }
        }
      ],
      "CommercialAutoAdditionalInsuredMunicipalitiesList": [
        {
          "CommercialAutoAdditionalInsuredMunicipalitiesDetail": {
          <#assign CommercialAutoAdditionalInsuredMunicipalitiesDetails="CommercialAutoAdditionalInsuredMunicipalitiesDetail"+x><#list CommercialAutoAdditionalInsuredMunicipalitiesDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
          }
        }
      ],
      "CommercialAutoCancellationNonrenewalNoticeToDesignatedPersonOrganizationList": [
        {
          "CommercialAutoCancellationNonrenewalNoticeToDesignatedPersonOrganizationDetail": {
          
          <#assign CancellationNonrenewalNotices="CancellationNonrenewalNotice"+x><#list CancellationNonrenewalNotices?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
           <#--   "NameID": "Sample_Data",
            "AddressID": "TestData",
            "NumberOfDaysNotice": "TestData"-->
          }
        }
      ],
      <#-- Need to add this -->
      "CommercialAutoAdditionalInsuredGaragesGrantorFranchiseList": [
        {
          "CommercialAutoAdditionalInsuredGaragesGrantorFranchiseDetail": {
             <#assign GrantorFranchises="GrantorFranchise"+x><#list GrantorFranchises?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
          }
        }
      ],
      "CommercialAutoFellowEmployeeCoverageForDesignatedEmployeesPositionsList": [
        {
          "CommercialAutoFellowEmployeeCoverageForDesignatedEmployeesPositionsDetail": {
            "NameID": "Sample_Data"
          }
        }
      ],
      <#-- Need to add this -->
      "CommercialAutoAdditionalInsuredLessorOfLeasedEquipmentList": [
        {
          "CommercialAutoAdditionalInsuredLessorOfLeasedEquipmentDetail": {
            <#assign AdditionalInsuredLessorOfLeaseds="AdditionalInsuredLessorOfLeased"+x><#list AdditionalInsuredLessorOfLeaseds?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
          }
        }
      ],
      "CommercialAutoLeasingOrRentalConcernsSecondLevelCovList": [
        {
          <#assign LeasingOrRentalConcernsSeconds="LeasingOrRentalConcernsSecond"+x><#list LeasingOrRentalConcernsSeconds?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
        }
      ],
     <#--   "CommercialAutoOptionalBenefitsCoverage": [
        {
          "CommercialAutoOptionalBenefitsCoverageDetail": {
           <#assign OptionalBenefitsCoverageDetails="OptionalBenefitsCoverageDetail"+x><#list OptionalBenefitsCoverageDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
          }
        }
      ],
      "CommercialAutoMotorCarrierEndorsement": [
        {
          "CommercialAutoMotorCarrierEndorsementDetail": {
            <#assign MotorCarrierEndorsementDetails="MotorCarrierEndorsementDetail"+x><#list MotorCarrierEndorsementDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
          }
        }
      ],-->
      
      "Truck": [
        {
          "TruckDetail": {
          	<#assign TruckLevels="TruckLevel"+x><#list TruckLevels?eval as result>     
        	"${result.atrib}":"${result.value}",
        	</#list> 
        	 "CommercialAutoAdditionalInsuredOwnerOfLeasedVehicleList": [
              {
                "CommercialAutoAdditionalInsuredOwnerOfLeasedVehicleDetail": {
                 <#assign CommercialAutoAdditionalInsuredOwnerOfLeasedVehicleDetails="TruckCommercialAutoAdditionalInsuredOwnerOfLeasedVehicleDetail"+x><#list CommercialAutoAdditionalInsuredOwnerOfLeasedVehicleDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list> 
                }
              }
            ],
            "CommercialAutoAdditionalNamedInsuredTruckList": [
              {
                "CommercialAutoAdditionalNamedInsuredTruckDetail": {
                  "NameOfOwner": "",
                  "DescribedOwnedMotorVehicle": ""
                }
              }
            ],
              "CommercialAutoLessorAdditionalInsuredAndLossPayeeList": [
                            {
                                "CommercialAutoLessorAdditionalInsuredAndLossPayeeDetail": {
                                    
              
                <#--  	<#assign LessorAdditionalInsuredAndLossPayeeDetails="TruckLessorAdditionalInsuredAndLossPayeeDetail"+x><#list LessorAdditionalInsuredAndLossPayeeDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list> -->
        	
        	"NameID": "",
              "AddressID": "",
              "DesignationOrDescriptionOfLeasedAutos": ""
                 }
              }
            ],
            
           "CommercialAutoAdditionalPersonalInjuryProtectionNewYorkList": [
              {        
                	<#assign AdditionalPersonalInjuryProtectionNewYorkLists="TruckAdditionalPersonalInjuryProtectionNewYorkList"+x><#list AdditionalPersonalInjuryProtectionNewYorkLists?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list> 
              }
              
            ],
           <#--   "CommercialAutoAdditionalInsuredAndLossPayee": {
              "CommercialAutoAdditionalInsuredAndLossPayeeDetail": [
              {
                	<#assign AdditionalInsuredAndLossPayeeDetails="TruckAdditionalInsuredAndLossPayeeDetail"+x><#list AdditionalInsuredAndLossPayeeDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list> 
              }
              ]
            },-->
            
            "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageUtahDetail": [
            {
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoveragePremium": "0.00",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageCalculatedPremium": "0.00",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoveragePipBasePremium": "",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoveragePrimaryFactor": "",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageAdditionalCoverageFactor": ""
              }
            ],
             "AudioVisualAndDataElectronicEquipmentCoverageList": [
              {
                "AudioVisualAndDataElectronicEquipmentCoverageLimit": "500",
                "CostNew": "500",
                "BasePremium": "20.000",
                "Premium": "31.00"
              }
            ],
            "CommercialAutoTapesRecordsAndDiscsCovList": [
              {
                "PreliminaryBasePremium": "9.000",
                "BasePremium": "13.896",
                "Premium": "14.00"
              }
            ],
            "CommercialAutoAutoLoanLeaseGapCovList": [
              {
                "CommercialAutoAutoLoanLeaseGapCovCollisionCoverage.CollisionCoverage": "Yes",
                "CommercialAutoAutoLoanLeaseGapCovOtherThanCollisionCoverage.OtherThanCollisionCoverage": "Yes",
                "CollisionCoverage_Factor": "0.070",
                "CollisionCoverage_Premium": "34.00",
                "OtherThanCollisionCoverage_Factor": "0.070",
                "OtherThanCollisionCoverage_Premium": "7.00",
                "Premium": "41.00",
                "CollisionCoverage_CalculatedPremium": "41.00",
                "OtherThanCollisionCoverage_CalculatedPremium": "8.00"
              }
            ],
            "CommercialAutoRentalReimbursementCovList": [
              {
                "DailyRentalAmount": "55",
                "MaximumRentalDays": "45",
                "RentalReimbursementCollisionCoverage_DailyRentalAmount_1": "55",
                "RentalReimbursementCollisionCoverage_MaximumRentalDays_1": "45",
                "RentalReimbursementCollisionCoverage_Premium": "41.00",
                "RentalReimbursementOtherThanCollisionCov_DailyRentalAmount_1": "55",
                "RentalReimbursementOtherThanCollisionCov_MaximumRentalDays_1": "45",
                "RentalReimbursementOtherThanCollisionCov_OtherThanCollisionFactor": "0.800",
                "RentalReimbursementOtherThanCollisionCov_Premium": "25.00",
                "RentalReimbursementCov_Premium": "66.00",
                "RentalReimbursementCov_Rate": "",
                "RentalReimbursementCollisionCoverage_CollisionFactor": "1.320",
                "RentalReimbursementCov_CalculatedPremium": "81.00",
                "RentalReimbursementCollisionCoverage_CalculatedPremium": "50.00",
                "RentalReimbursementOtherThanCollisionCov_CalculatedPremium": "31.00",
                "DesignationOrDescriptionOfCoveredAutosComprehensive": "",
                "DesignationOrDescriptionOfCoveredAutosSpecifiedCausesOfLoss": "",
                "DesignationOrDescriptionOfCoveredAutosCollision": "",
                "CommercialAutoRentalReimbursementCov.OtherThanCollisionCoverageAnyOnePeriod": "",
                "CommercialAutoRentalReimbursementCov.CollisionCoverageAnyOnePeriod": "",
                "CommercialAutoRentalReimbursementCov.DesignationorDescriptionofCoveredAuto": ""
              }
            ],
             "CommercialAutoMichiganPropertyDamageLiabilityCoverageBuybackList": [
              {
                "PreliminaryBasePremium": "",
                "BasePremium": "",
                "Premium": ""
              }
            ],
             "CommercialAutoArkansasPersonalInjuryProtectionList": [
              {
                "AccidentalDeathBenefitCoverage_BasePremium": "",
                "AccidentalDeathBenefitCoverage_PreliminaryBasePremium": "",
                "AccidentalDeathBenefitCoverage_CoverageUseFactor": "",
                "AccidentalDeathBenefitCoverage_Premium": "0.000",
                "AccidentalDeathBenefitCoverage_SafetyScoreDiscountFactor": "",
                "WorkLossCoverage_BasePremium": "",
                "WorkLossCoverage_PreliminaryBasePremium": "",
                "WorkLossCoverage_CoverageUseFactor": "",
                "WorkLossCoverage_Premium": "0.000",
                "WorkLossCoverage_SafetyScoreDiscountFactor": "",
                "Premium": "0.00",
                "AccidentalDeathBenefitCoverage_PrimaryFactor": "",
                "WorkLossCoverage_PrimaryFactor": "",
                "CalculatedPremium": "0.00",
                "AccidentalDeathBenefitCoverage_CalculatedPremium": "0.00",
                "WorkLossCoverage_CalculatedPremium": "0.00",
                "MedicalExpenseOwnedAutoUnderCoverageForm": "",
                "MedicalExpenseOwnedByYou": "",
                "MedicalExpensePrivatePassengerOwnedByYou": "",
                "MedicalExpenseOther": "",
                "WorkLossOwnedAutoUnderCoverageForm": "",
                "WorkLossOwnedAutoByYou": "",
                "WorkLossOther": "",
                "AccidentalDeathOwnedAutoUnderCoverageForm": "",
                "AccidentalDeathOwnedAutoByYou": "",
                "AccidentalDeathOther": ""
              }
            ],
           <#-- "commercialAutoMultiPurposeEquipment": {
              "CommercialAutoMultiPurposeEquipmentDetail": [
              {
                	<#assign MultiPurposeEquipmentDetails="TruckMultiPurposeEquipmentDetail"+x><#list MultiPurposeEquipmentDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list> 
                 }
              ]
            },
            "CommercialAutoTexasIncreasedLimitsOtherThanDesignatedDrivers": {
              "CommercialAutoTexasIncreasedLimitsOtherThanDesignatedDriversDetail": [
              {
                	<#assign TexasIncreasedLimitsOtherThanDesignatedDriversDetails="TruckTexasIncreasedLimitsOtherThanDesignatedDriversDetail"+x><#list TexasIncreasedLimitsOtherThanDesignatedDriversDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list> 
                 }
              ]
            },-->
            "Forms": {
                "FormDetail": [
                <#assign j=1>
	        <#list 1..Trucklevelarray[0] as xx>
	        {
	        	<#assign TruckForms="TruckForm"+j+x><#list TruckForms?eval as result>         	    
	        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
	        	</#list> 
	        }<#if xx?is_last><#else>,</#if><#assign j=j+1>
		    </#list>
            ]
            
          }
          }
        }
      ],
      "Special": [
        {
          "SpecialDetail": {
          	<#assign SpecialTypesLevels="SpecialTypesLevel"+x><#list SpecialTypesLevels?eval as result>     
        	"${result.atrib}":"${result.value}",
        	</#list>
          "CommercialAutoAdditionalPersonalInjuryProtectionNewYorkList": [
              {
                <#assign AdditionalPersonalInjuryProtectionNewYorkLists="SpecialAdditionalPersonalInjuryProtectionNewYorkList"+x><#list AdditionalPersonalInjuryProtectionNewYorkLists?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                }
              ],
           
        <#--     "CommercialAutoAdditionalInsuredAndLossPayee": {
              "CommercialAutoAdditionalInsuredAndLossPayeeDetail": [
                {
                	<#assign AdditionalInsuredAndLossPayeeDetails="SpecialAdditionalInsuredAndLossPayeeDetail"+x><#list AdditionalInsuredAndLossPayeeDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                 }
              ]
            }, 
            
            "CommercialAutoLessorAdditionalInsuredAndLossPayeeList": {
              "CommercialAutoLessorAdditionalInsuredAndLossPayeeDetail": [
                {
                	<#assign LessorAdditionalInsuredAndLossPayeeDetails="SpecialLessorAdditionalInsuredAndLossPayeeDetail"+x><#list LessorAdditionalInsuredAndLossPayeeDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                 }
              ]
            },
            "CommercialAutoAdditionalInsuredOwnerOfLeasedVehicleList": [
              {
                "CommercialAutoAdditionalInsuredOwnerOfLeasedVehicleDetail": {
                  "NameID": "",
                  "AIBDescriptionOfLeasedAutos": ""
                }
              }
            ],-->
            
            "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageUtahDetail": [
              {
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoveragePremium": "0.00",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageCalculatedPremium": "0.00",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoveragePipBasePremium": "",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageAdditionalCoverageFactor": "",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageNoFaultFlatRateFactor": "",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageNumberOfRepossessedAutos": "",
              "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageTypeFactor":""
              }
            ],
            "CommercialAutoAutoLoanLeaseGapCovList": [
              {
                "CommercialAutoAutoLoanLeaseGapCovCollisionCoverage.CollisionCoverage": "Yes",
                "CommercialAutoAutoLoanLeaseGapCovOtherThanCollisionCoverage.OtherThanCollisionCoverage": "Yes",
                "CollisionCoverage_Factor": "0.070",
                "CollisionCoverage_Premium": "2.00",
                "OtherThanCollisionCoverage_Factor": "0.070",
                "OtherThanCollisionCoverage_Premium": "2.00",
                "Premium": "4.00",
                "CollisionCoverage_CalculatedPremium": "3.00",
                "OtherThanCollisionCoverage_CalculatedPremium": "2.00"
              }
            ],
            "CommercialAutoRentalReimbursementCovList": [
              {
                "DailyRentalAmount": "55",
                "MaximumRentalDays": "45",
                "RentalReimbursementCollisionCoverage_DailyRentalAmount_1": "55",
                "RentalReimbursementCollisionCoverage_MaximumRentalDays_1": "45",
                "RentalReimbursementCollisionCoverage_Premium": "41.00",
                "RentalReimbursementOtherThanCollisionCov_DailyRentalAmount_1": "55",
                "RentalReimbursementOtherThanCollisionCov_MaximumRentalDays_1": "45",
                "RentalReimbursementOtherThanCollisionCov_OtherThanCollisionFactor": "0.800",
                "RentalReimbursementOtherThanCollisionCov_Premium": "25.00",
                "RentalReimbursementCov_Premium": "66.00",
                "RentalReimbursementCov_Rate": "",
                "RentalReimbursementCollisionCoverage_CollisionFactor": "1.320",
                "RentalReimbursementCov_CalculatedPremium": "81.00",
                "RentalReimbursementCollisionCoverage_CalculatedPremium": "50.00",
                "RentalReimbursementOtherThanCollisionCov_CalculatedPremium": "31.00"
              }
            ],
            "CommercialAutoTapesRecordsAndDiscsCovList": [
              {
                "PreliminaryBasePremium": "9.000",
                "BasePremium": "13.896",
                "Premium": "14.00"
              }
            ],
             "AudioVisualAndDataElectronicEquipmentCoverageList": [
              {
                "AudioVisualAndDataElectronicEquipmentCoverageLimit": "500",
                "CostNew": "500",
                "BasePremium": "20.000",
                "Premium": "31.00"
              }
            ],
             "CommercialAutoLeasingRentalConcernsConversionEmbezzlementSecretionCovList": [
                            {
                                "CalculatedPremium": "144.0",
                                "InsuredValue": "15000.000",
                                "LCMPhysicalDamage": "1.544",
                                "Premium": "118.00",
                                "Rate": "0.620"
                            }
                        ],
                        "CommercialAutoMobileHomesContentsCovList": [
                            {
                                "AdditionalCoverageFactor": "1.000",
                                "BasePremium": "61.000",
                                "CalculatedPremium": "0.0",
                                "CoverageType": "Stated Amount - Specified Causes of Loss",
                                "Deductible": "",
                                "Limit": "10001",
                                "PolicyTermPremium": "0.00",
                                "TypeFactor": ""
                            }
                        ],
            "CommercialAutoArkansasPersonalInjuryProtectionList": [
              {
                "AccidentalDeathBenefitCoverage_BasePremium": "",
                "AccidentalDeathBenefitCoverage_PreliminaryBasePremium": "",
                "AccidentalDeathBenefitCoverage_Premium": "0.000",
                "WorkLossCoverage_TypeFactor": "",
                "AccidentalDeathBenefitCoverage_TypeFactor": "",
                "WorkLossCoverage_BasePremium": "",
                "WorkLossCoverage_PreliminaryBasePremium": "",
                "WorkLossCoverage_Premium": "0.000",
                "Premium": "0.00",
                "CalculatedPremium": "0.00",
                "AccidentalDeathBenefitCoverage_CalculatedPremium": "0.00",
                "WorkLossCoverage_CalculatedPremium": "0.00",
                "MedicalExpenseOwnedAutoUnderCoverageForm": "",
                "MedicalExpenseOwnedByYou": "",
                "MedicalExpensePrivatePassengerOwnedByYou": "",
                "MedicalExpenseOther": "",
                "WorkLossOwnedAutoUnderCoverageForm": "",
                "WorkLossOwnedAutoByYou": "",
                "WorkLossOther": "",
                "AccidentalDeathOwnedAutoUnderCoverageForm": "",
                "AccidentalDeathOwnedAutoByYou": "",
                "AccidentalDeathOther": ""
              }
            ],
            "CommercialAutoMichiganPropertyDamageLiabilityCoverageBuybackList": [
              {
                "PreliminaryBasePremium": "",
                "BasePremium": "",
                "Premium": ""
              }
            ],
            
        <#--    "commercialAutoMultiPurposeEquipment": {
              "CommercialAutoMultiPurposeEquipmentDetail": [
                {
                	<#assign MultiPurposeEquipmentDetails="SpecialMultiPurposeEquipmentDetail"+x><#list MultiPurposeEquipmentDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                 }
              ]
            },
            
            "CommercialAutoTexasIncreasedLimitsOtherThanDesignatedDrivers": {
              "CommercialAutoTexasIncreasedLimitsOtherThanDesignatedDriversDetail": [
                {
                	<#assign TexasIncreasedLimitsOtherThanDesignatedDriversDetails="SpecialTexasIncreasedLimitsOtherThanDesignatedDriversDetail"+x><#list TexasIncreasedLimitsOtherThanDesignatedDriversDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                 }
              ]
            },--> 
            "Forms": {
                "FormDetail": [<#assign j=1>
	        <#list 1..SpecialTypeslevelarray[0] as xx>
	        {
	        	<#assign SpecialTypesForms="SpecialTypesForm"+j+x><#list SpecialTypesForms?eval as result>         	    
	        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
	        	</#list> 
	        }<#if xx?is_last><#else>,</#if><#assign j=j+1>
		    </#list>
            ]
          }
          }
        }
      ],

      "PrivatePassenger": [
        {
          "PrivatePassengerDetail": {
          	<#assign PrivatePassengerLevels="PrivatePassengerLevel"+x><#list PrivatePassengerLevels?eval as result>     
        	"${result.atrib}":"${result.value}",
        	</#list>
          "CommercialAutoAdditionalPersonalInjuryProtectionNewYorkList": [
              {
               
                	<#assign AdditionalPersonalInjuryProtectionNewYorkLists="PrivateAdditionalPersonalInjuryProtectionNewYorkList"+x><#list AdditionalPersonalInjuryProtectionNewYorkLists?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                 }
              
            ],
          <#--    "CommercialAutoAdditionalInsuredAndLossPayee": {
              "CommercialAutoAdditionalInsuredAndLossPayeeDetail": [
              {
                	<#assign AdditionalInsuredAndLossPayeeDetails="PrivateAdditionalInsuredAndLossPayeeDetail"+x><#list AdditionalInsuredAndLossPayeeDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                 }
               ]
            },-->
            "CommercialAutoLessorAdditionalInsuredAndLossPayeeList": [
            {
              "CommercialAutoLessorAdditionalInsuredAndLossPayeeDetail": 
              {
           <#--      	<#assign LessorAdditionalInsuredAndLossPayeeDetails="PrivateLessorAdditionalInsuredAndLossPayeeDetail"+x><#list LessorAdditionalInsuredAndLossPayeeDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>--> 
        	
        	"NameID": "",
              "AddressID": "",
              "DesignationOrDescriptionOfLeasedAutos": ""
                 }
               }
            ],
         <#--    "commercialAutoMultiPurposeEquipment": {
              "CommercialAutoMultiPurposeEquipmentDetail": [
              {
                	<#assign MultiPurposeEquipmentDetails="PrivateMultiPurposeEquipmentDetail"+x><#list MultiPurposeEquipmentDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                 }
               ]
            },
            "CommercialAutoTexasIncreasedLimitsOtherThanDesignatedDrivers": {
              "CommercialAutoTexasIncreasedLimitsOtherThanDesignatedDriversDetail": [
              {
                	<#assign TexasIncreasedLimitsOtherThanDesignatedDriversDetails="PrivateTexasIncreasedLimitsOtherThanDesignatedDriversDetail"+x><#list TexasIncreasedLimitsOtherThanDesignatedDriversDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                 }
               ]
            }, -->
            "CommercialAutoAdditionalInsuredOwnerOfLeasedVehicleList": [
              {
                "CommercialAutoAdditionalInsuredOwnerOfLeasedVehicleDetail": {
                  "NameID": "",
                  "AIBDescriptionOfLeasedAutos": ""
                }
              }
            ],
            "CommercialAutoAdditionalNamedInsuredPrivatePassengerList": [
              {
                "CommercialAutoAdditionalNamedInsuredPrivatePassengerDetail": {
                  "NameOfOwner": "",
                  "DescribedOwnedMotorVehicle": ""
                }
              }
            ],
            "CommercialAutoWashingtonAutoLoanCoverageGapCovList": [
              {
                "CommercialAutoAutoLoanLeaseGapCovCollisionCoverage.CollisionCoverage": "Yes",
                "CommercialAutoAutoLoanLeaseGapCovOtherThanCollisionCoverage.OtherThanCollisionCoverage": "Yes",
                "CollisionCoverage_Factor": "",
                "CollisionCoverage_Premium": "0.0",
                "OtherThanCollisionCoverage_Factor": "",
                "OtherThanCollisionCoverage_Premium": "0.0",
                "Premium": "",
                "CollisionCoverage_CalculatedPremium": "0.0",
                "OtherThanCollisionCoverage_CalculatedPremium": "0.0"
              }
            ],
            "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageUtahDetail": [
              {
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoveragePremium": "0.00",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageCalculatedPremium": "0.0",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoveragePipBasePremium": "",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageAdditionalCoverageFactor": ""
              }
            ],
              "CommercialAutoAutoLoanLeaseGapCovList": [
              {
                "CommercialAutoAutoLoanLeaseGapCovCollisionCoverage.CollisionCoverage": "Yes",
                "CommercialAutoAutoLoanLeaseGapCovOtherThanCollisionCoverage.OtherThanCollisionCoverage": "Yes",
                "CollisionCoverage_Factor": "0.070",
                "CollisionCoverage_Premium": "3.00",
                "OtherThanCollisionCoverage_Factor": "0.070",
                "OtherThanCollisionCoverage_Premium": "0.00",
                "Premium": "3.00",
                "CollisionCoverage_CalculatedPremium": "4.00",
                "OtherThanCollisionCoverage_CalculatedPremium": "0.00"
              }
            ],
            "CommercialAutoRentalReimbursementCovList": [
              {
                "DailyRentalAmount": "55",
                "MaximumRentalDays": "45",
                "RentalReimbursementCollisionCoverage_DailyRentalAmount_1": "55",
                "RentalReimbursementCollisionCoverage_MaximumRentalDays_1": "45",
                "RentalReimbursementCollisionCoverage_Premium": "41.00",
                "RentalReimbursementOtherThanCollisionCov_DailyRentalAmount_1": "55",
                "RentalReimbursementOtherThanCollisionCov_MaximumRentalDays_1": "45",
                "RentalReimbursementOtherThanCollisionCov_OtherThanCollisionFactor": "0.800",
                "RentalReimbursementOtherThanCollisionCov_Premium": "25.00",
                "RentalReimbursementCov_Premium": "66.00",
                "RentalReimbursementCov_Rate": "",
                "RentalReimbursementCollisionCoverage_CollisionFactor": "1.320",
                "RentalReimbursementCov_CalculatedPremium": "81.00",
                "RentalReimbursementCollisionCoverage_CalculatedPremium": "50.00",
                "RentalReimbursementOtherThanCollisionCov_CalculatedPremium": "31.00",
                "DesignationOrDescriptionOfCoveredAutosComprehensive": "",
                "DesignationOrDescriptionOfCoveredAutosSpecifiedCausesOfLoss": "",
                "DesignationOrDescriptionOfCoveredAutosCollision": "",
                "CommercialAutoRentalReimbursementCov.OtherThanCollisionCoverageAnyOnePeriod": "",
                "CommercialAutoRentalReimbursementCov.CollisionCoverageAnyOnePeriod": "",
                "CommercialAutoRentalReimbursementCov.DesignationorDescriptionofCoveredAuto": ""
              }
            ],
            "CommercialAutoTapesRecordsAndDiscsCovList": [
              {
                "PreliminaryBasePremium": "9.000",
                "BasePremium": "13.896",
                "Premium": "14.00"
              }
            ],
            "AudioVisualAndDataElectronicEquipmentCoverageList": [
              {
                "AudioVisualAndDataElectronicEquipmentCoverageLimit": "500",
                "CostNew": "300",
                "BasePremium": "20.000",
                "Premium": "31.00"
              }
            ],
           
            "CommercialAutoArkansasPersonalInjuryProtectionList": [
              {
                "AccidentalDeathBenefitCoverage_BasePremium": "",
                "AccidentalDeathBenefitCoverage_PreliminaryBasePremium": "",
                "AccidentalDeathBenefitCoverage_CoverageUseFactor": "",
                "AccidentalDeathBenefitCoverage_liabilityCombinedRatingFactor": "",
                "AccidentalDeathBenefitCoverage_Premium": "0.000",
                "AccidentalDeathBenefitCoverage_SafetyScoreDiscountFactor": "",
                "AccidentalDeathBenefitCoverage_TypeFactor": "",
                "WorkLossCoverage_BasePremium": "",
                "WorkLossCoverage_PreliminaryBasePremium": "",
                "WorkLossCoverage_CoverageUseFactor": "",
                "WorkLossCoverage_liabilityCombinedRatingFactor": "",
                "WorkLossCoverage_Premium": "0.000",
                "WorkLossCoverage_SafetyScoreDiscountFactor": "",
                "WorkLossCoverage_TypeFactor": "",
                "Premium": "0.00",
                "WorkLossCoverage_PrimaryFactor": "",
                "CalculatedPremium": "0.00",
                "AccidentalDeathBenefitCoverage_CalculatedPremium": "0.00",
                "WorkLossCoverage_CalculatedPremium": "0.00",
                "MedicalExpenseOwnedAutoUnderCoverageForm": "",
                "MedicalExpenseOwnedByYou": "",
                "MedicalExpensePrivatePassengerOwnedByYou": "",
                "MedicalExpenseOther": "",
                "WorkLossOwnedAutoUnderCoverageForm": "",
                "WorkLossOwnedAutoByYou": "",
                "WorkLossOther": "",
                "AccidentalDeathOwnedAutoUnderCoverageForm": "",
                "AccidentalDeathOwnedAutoByYou": "",
                "AccidentalDeathOther": ""
              }
            ],
            "CommercialAutoMichiganPropertyDamageLiabilityCoverageBuybackList": [
              {
                "PreliminaryBasePremium": "",
                "BasePremium": "",
                "Premium": ""
              }
            ],
           "Forms": {
                "FormDetail": [<#assign j=1>
	        <#list 1..Pptlevelarray[0] as xx>
	        {
	        	<#assign PptForms="PptForm"+j+x><#list PptForms?eval as result>         	    
	        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
	        	</#list> 
	        }<#if xx?is_last><#else>,</#if><#assign j=j+1>
		    </#list>
            ]
          }
          }
        }
      ],
      "ZoneRated": [
        {
          "ZoneRatedDetail": {
          <#assign ZoneRatedLevels="ZoneRatedLevel"+x><#list ZoneRatedLevels?eval as result>     
        	"${result.atrib}":"${result.value}",
        	</#list>
          "CommercialAutoAdditionalPersonalInjuryProtectionNewYorkList": [
              {
                	<#assign AdditionalPersonalInjuryProtectionNewYorkLists="ZoneRatedAdditionalPersonalInjuryProtectionNewYorkList"+x><#list AdditionalPersonalInjuryProtectionNewYorkLists?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                 }
              
            ],
        <#--     "CommercialAutoAdditionalInsuredAndLossPayee": {
              "CommercialAutoAdditionalInsuredAndLossPayeeDetail": [
              {
                	<#assign AdditionalInsuredAndLossPayeeDetails="ZoneRatedAdditionalInsuredAndLossPayeeDetail"+x><#list AdditionalInsuredAndLossPayeeDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                }
              ]
            },-->
             "CommercialAutoLessorAdditionalInsuredAndLossPayeeList": [
            {
              "CommercialAutoLessorAdditionalInsuredAndLossPayeeDetail": 
              {
           <#--      	<#assign LessorAdditionalInsuredAndLossPayeeDetails="PrivateLessorAdditionalInsuredAndLossPayeeDetail"+x><#list LessorAdditionalInsuredAndLossPayeeDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>--> 
        	
        	"NameID": "",
              "AddressID": "",
              "DesignationOrDescriptionOfLeasedAutos": ""
                 }
               }
            ],
             "CommercialAutoAdditionalInsuredOwnerOfLeasedVehicleList": [
              {
                "CommercialAutoAdditionalInsuredOwnerOfLeasedVehicleDetail": {
                  "NameID": "",
                  "AIBDescriptionOfLeasedAutos": ""
                }
              }
            ],
            
            "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageUtahDetail": [
              {
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoveragePremium": "0.00",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageCalculatedPremium": "0.00",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoveragePipBasePremium": "",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageAdditionalCoverageFactor": "",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoveragePipFactor": "",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageInterstateBusFactor": "",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageFleetFactor": "",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageLiabilityCombinedRatingFactor": ""
              }
            ],
            "CommercialAutoAutoLoanLeaseGapCovList": [
              {
                "CommercialAutoAutoLoanLeaseGapCovCollisionCoverage.CollisionCoverage": "Yes",
                "CommercialAutoAutoLoanLeaseGapCovOtherThanCollisionCoverage.OtherThanCollisionCoverage": "Yes",
                "CollisionCoverage_Factor": "0.070",
                "CollisionCoverage_Premium": "2.00",
                "OtherThanCollisionCoverage_Factor": "0.070",
                "OtherThanCollisionCoverage_Premium": "2.00",
                "Premium": "4.00",
                "CollisionCoverage_CalculatedPremium": "3.00",
                "OtherThanCollisionCoverage_CalculatedPremium": "2.00"
              }
            ],
            "CommercialAutoRentalReimbursementCovList": [
              {
                "DailyRentalAmount": "55",
                "MaximumRentalDays": "45",
                "RentalReimbursementCollisionCoverage_DailyRentalAmount_1": "55",
                "RentalReimbursementCollisionCoverage_MaximumRentalDays_1": "45",
                "RentalReimbursementCollisionCoverage_Premium": "41.00",
                "RentalReimbursementOtherThanCollisionCov_DailyRentalAmount_1": "55",
                "RentalReimbursementOtherThanCollisionCov_MaximumRentalDays_1": "45",
                "RentalReimbursementOtherThanCollisionCov_OtherThanCollisionFactor": "0.800",
                "RentalReimbursementOtherThanCollisionCov_Premium": "25.00",
                "RentalReimbursementCov_Premium": "66.00",
                "RentalReimbursementCov_Rate": "",
                "RentalReimbursementCollisionCoverage_CollisionFactor": "1.320",
                "RentalReimbursementCov_CalculatedPremium": "81.00",
                "RentalReimbursementCollisionCoverage_CalculatedPremium": "50.00",
                "RentalReimbursementOtherThanCollisionCov_CalculatedPremium": "31.00",
                "DesignationOrDescriptionOfCoveredAutosComprehensive": "",
                "DesignationOrDescriptionOfCoveredAutosSpecifiedCausesOfLoss": "",
                "DesignationOrDescriptionOfCoveredAutosCollision": "",
                "CommercialAutoRentalReimbursementCov.OtherThanCollisionCoverageAnyOnePeriod": "",
                "CommercialAutoRentalReimbursementCov.CollisionCoverageAnyOnePeriod": "",
                "CommercialAutoRentalReimbursementCov.DesignationorDescriptionofCoveredAuto": ""
              }
            ],
            "CommercialAutoTapesRecordsAndDiscsCovList": [
              {
                "PreliminaryBasePremium": "9.000",
                "BasePremium": "13.896",
                "Premium": "14.00"
              }
            ],
             "AudioVisualAndDataElectronicEquipmentCoverageList": [
              {
                "AudioVisualAndDataElectronicEquipmentCoverageLimit": "500",
                "CostNew": "500",
                "BasePremium": "20.000",
                "Premium": "31.00",
                 "TotalModificationFactorPhysicalDamage": ""
              }
            ],
            
            "CommercialAutoArkansasPersonalInjuryProtectionList": [
              {
                "AccidentalDeathBenefitCoverage_BasePremium": "",
                "AccidentalDeathBenefitCoverage_PreliminaryBasePremium": "",
               
                "AccidentalDeathBenefitCoverage_Premium": "0.000",
               
                "WorkLossCoverage_BasePremium": "",
                "WorkLossCoverage_PreliminaryBasePremium": "",
               
                "WorkLossCoverage_Premium": "0.000",
               
                "Premium": "0.00",
                "AccidentalDeathBenefitCoverage_FleetFactor": "",
               
                "AccidentalDeathBenefitCoverage_PrimaryFactor": "",
                "WorkLossCoverage_FleetFactor": "",
                
                "WorkLossCoverage_PrimaryFactor": "",
                "CalculatedPremium": "0.00",
                "AccidentalDeathBenefitCoverage_CalculatedPremium": "0.00",
                "WorkLossCoverage_CalculatedPremium": "0.00",
                "MedicalExpenseOwnedAutoUnderCoverageForm": "",
                "MedicalExpenseOwnedByYou": "",
                "MedicalExpensePrivatePassengerOwnedByYou": "",
                "MedicalExpenseOther": "",
                "WorkLossOwnedAutoUnderCoverageForm": "",
                "WorkLossOwnedAutoByYou": "",
                "WorkLossOther": "",
                "AccidentalDeathOwnedAutoUnderCoverageForm": "",
                "AccidentalDeathOwnedAutoByYou": "",
                "AccidentalDeathOther": ""
              }
            ],
            "CommercialAutoMichiganPropertyDamageLiabilityCoverageBuybackList": [
              {
                "PreliminaryBasePremium": "",
                "BasePremium": "",
                "Premium": ""
              }
            ],
             "CommercialAutoAdditionalNamedInsuredZoneRatedList": [
              {
                "CommercialAutoAdditionalNamedInsuredZoneRatedDetail": {
                  "NameOfOwner": "",
                  "DescribedOwnedMotorVehicle": ""
                }
              }
            ],
          <#--    "commercialAutoMultiPurposeEquipment": {
              "CommercialAutoMultiPurposeEquipmentDetail": [
              {
                	<#assign MultiPurposeEquipmentDetails="ZoneRatedMultiPurposeEquipmentDetail"+x><#list MultiPurposeEquipmentDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                }
              ]
            },
            "CommercialAutoTexasIncreasedLimitsOtherThanDesignatedDrivers": {
              "CommercialAutoTexasIncreasedLimitsOtherThanDesignatedDriversDetail": [
              {
                	<#assign TexasIncreasedLimitsOtherThanDesignatedDriversDetails="ZoneRatedTexasIncreasedLimitsOtherThanDesignatedDriversDetail"+x><#list TexasIncreasedLimitsOtherThanDesignatedDriversDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                }
              ]
            },-->
           "Forms": {
                "FormDetail": [<#assign j=1>
	        <#list 1..ZoneRatedlevelarray[0] as xx>
	        {
	        	<#assign ZoneRatedForms="ZoneRatedForm"+j+x><#list ZoneRatedForms?eval as result>         	    
	        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
	        	</#list> 
	        }<#if xx?is_last><#else>,</#if><#assign j=j+1>
		    </#list>
            ]
          }
          }
        }
      ],
      "PublicTransportation": [
        {
          "PublicTransportationDetail": {
          	<#assign PublicTransportationLevels="PublicTransportationLevel"+x><#list PublicTransportationLevels?eval as result>     
        	"${result.atrib}":"${result.value}",
        	</#list>
            "CommercialAutoAdditionalPersonalInjuryProtectionNewYorkList": [
              {
                	<#assign AdditionalPersonalInjuryProtectionNewYorkLists="PublicTransAdditionalPersonalInjuryProtectionNewYorkList"+x><#list AdditionalPersonalInjuryProtectionNewYorkLists?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                 }
              
            ],
           <#--   "CommercialAutoAdditionalInsuredAndLossPayee": {
              "CommercialAutoAdditionalInsuredAndLossPayeeDetail": [
              {
                	<#assign AdditionalInsuredAndLossPayeeDetails="PublicTransAdditionalInsuredAndLossPayeeDetail"+x><#list AdditionalInsuredAndLossPayeeDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                }
              ]
            },-->
            "CommercialAutoAdditionalNamedInsuredPublicTransportationList": [
              {
                "CommercialAutoAdditionalNamedInsuredPublicTransportationDetail": {
                  "NameOfOwner": "",
                  "DescribedOwnedMotorVehicle": ""
                }
              }
            ],
            "CommercialAutoLessorAdditionalInsuredAndLossPayeeList": [
            {
              "CommercialAutoLessorAdditionalInsuredAndLossPayeeDetail": 
              {
           <#--      	<#assign LessorAdditionalInsuredAndLossPayeeDetails="PrivateLessorAdditionalInsuredAndLossPayeeDetail"+x><#list LessorAdditionalInsuredAndLossPayeeDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>--> 
        	
        	"NameID": "",
              "AddressID": "",
              "DesignationOrDescriptionOfLeasedAutos": ""
                 }
               }
            ],
            "CommercialAutoAdditionalInsuredOwnerOfLeasedVehicleList": [
              {
                "CommercialAutoAdditionalInsuredOwnerOfLeasedVehicleDetail": {
                  "NameID": "",
                  "AIBDescriptionOfLeasedAutos": ""
                }
              }
            ],
            
            "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageUtahDetail": [
              {
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoveragePremium": "0.00",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageCalculatedPremium": "0.00",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoveragePipBasePremium": "",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageAdditionalCoverageFactor": "",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoveragePipFactor": "",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageInterstateBusFactor": "",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageFleetFactor": "",
                "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageLiabilityCombinedRatingFactor": ""
              }
            ],
            "CommercialAutoAutoLoanLeaseGapCovList": [
              {
                "CommercialAutoAutoLoanLeaseGapCovCollisionCoverage.CollisionCoverage": "Yes",
                "CommercialAutoAutoLoanLeaseGapCovOtherThanCollisionCoverage.OtherThanCollisionCoverage": "Yes",
                "CollisionCoverage_Factor": "0.070",
                "CollisionCoverage_Premium": "2.00",
                "OtherThanCollisionCoverage_Factor": "0.070",
                "OtherThanCollisionCoverage_Premium": "2.00",
                "Premium": "4.00",
                "CollisionCoverage_CalculatedPremium": "3.00",
                "OtherThanCollisionCoverage_CalculatedPremium": "2.00"
              }
            ],
            "CommercialAutoRentalReimbursementCovList": [
              {
                "DailyRentalAmount": "55",
                "MaximumRentalDays": "45",
                "RentalReimbursementCollisionCoverage_DailyRentalAmount_1": "55",
                "RentalReimbursementCollisionCoverage_MaximumRentalDays_1": "45",
                "RentalReimbursementCollisionCoverage_Premium": "41.00",
                "RentalReimbursementOtherThanCollisionCov_DailyRentalAmount_1": "55",
                "RentalReimbursementOtherThanCollisionCov_MaximumRentalDays_1": "45",
                "RentalReimbursementOtherThanCollisionCov_OtherThanCollisionFactor": "0.800",
                "RentalReimbursementOtherThanCollisionCov_Premium": "25.00",
                "RentalReimbursementCov_Premium": "66.00",
                "RentalReimbursementCov_Rate": "",
                "RentalReimbursementCollisionCoverage_CollisionFactor": "1.320",
                "RentalReimbursementCov_CalculatedPremium": "81.00",
                "RentalReimbursementCollisionCoverage_CalculatedPremium": "50.00",
                "RentalReimbursementOtherThanCollisionCov_CalculatedPremium": "31.00",
                "DesignationOrDescriptionOfCoveredAutosComprehensive": "",
                "DesignationOrDescriptionOfCoveredAutosSpecifiedCausesOfLoss": "",
                "DesignationOrDescriptionOfCoveredAutosCollision": "",
                "CommercialAutoRentalReimbursementCov.OtherThanCollisionCoverageAnyOnePeriod": "",
                "CommercialAutoRentalReimbursementCov.CollisionCoverageAnyOnePeriod": "",
                "CommercialAutoRentalReimbursementCov.DesignationorDescriptionofCoveredAuto": ""
              }
            ],
            "CommercialAutoTapesRecordsAndDiscsCovList": [
              {
                "PreliminaryBasePremium": "9.000",
                "BasePremium": "13.896",
                "Premium": "14.00"
              }
            ],
             "AudioVisualAndDataElectronicEquipmentCoverageList": [
              {
                "AudioVisualAndDataElectronicEquipmentCoverageLimit": "500",
                "CostNew": "500",
                "BasePremium": "20.000",
                "Premium": "31.00"
              }
            ],
            
            "CommercialAutoArkansasPersonalInjuryProtectionList": [
              {
                "AccidentalDeathBenefitCoverage_BasePremium": "",
                "AccidentalDeathBenefitCoverage_PreliminaryBasePremium": "",
                "AccidentalDeathBenefitCoverage_liabilityCombinedRatingFactor": "",
                "AccidentalDeathBenefitCoverage_Premium": "0.000",
                "AccidentalDeathBenefitCoverage_SafetyScoreDiscountFactor": "",
                "WorkLossCoverage_BasePremium": "",
                "WorkLossCoverage_PreliminaryBasePremium": "",
                "WorkLossCoverage_liabilityCombinedRatingFactor": "",
                "WorkLossCoverage_Premium": "0.000",
                "WorkLossCoverage_SafetyScoreDiscountFactor": "",
                "Premium": "0.00",
                "AccidentalDeathBenefitCoverage_FleetFactor": "",
                "AccidentalDeathBenefitCoverage_MechanicalLiftFactor": "",
                "AccidentalDeathBenefitCoverage_PrimaryFactor": "",
                "WorkLossCoverage_FleetFactor": "",
                "WorkLossCoverage_MechanicalLiftFactor": "",
                "WorkLossCoverage_PrimaryFactor": "",
                "CalculatedPremium": "0.00",
                "AccidentalDeathBenefitCoverage_CalculatedPremium": "0.00",
                "WorkLossCoverage_CalculatedPremium": "0.00",
                "MedicalExpenseOwnedAutoUnderCoverageForm": "",
                "MedicalExpenseOwnedByYou": "",
                "MedicalExpensePrivatePassengerOwnedByYou": "",
                "MedicalExpenseOther": "",
                "WorkLossOwnedAutoUnderCoverageForm": "",
                "WorkLossOwnedAutoByYou": "",
                "WorkLossOther": "",
                "AccidentalDeathOwnedAutoUnderCoverageForm": "",
                "AccidentalDeathOwnedAutoByYou": "",
                "AccidentalDeathOther": ""
              }
            ],
            "CommercialAutoMichiganPropertyDamageLiabilityCoverageBuybackList": [
              {
                "PreliminaryBasePremium": "",
                "BasePremium": "",
                "Premium": ""
              }
            ],
           <#--   "commercialAutoMultiPurposeEquipment": {
              "CommercialAutoMultiPurposeEquipmentDetail": [
              {
                	<#assign MultiPurposeEquipmentDetails="PublicTransMultiPurposeEquipmentDetail"+x><#list MultiPurposeEquipmentDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                }
              ]
            },
            "CommercialAutoTexasIncreasedLimitsOtherThanDesignatedDrivers": {
              "CommercialAutoTexasIncreasedLimitsOtherThanDesignatedDriversDetail": [
              {
                	<#assign TexasIncreasedLimitsOtherThanDesignatedDriversDetails="PublicTransTexasIncreasedLimitsOtherThanDesignatedDriversDetail"+x><#list TexasIncreasedLimitsOtherThanDesignatedDriversDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                }
              ]
            },-->
            "Forms": {
                "FormDetail": [<#assign j=1>
	        <#list 1..PublicTranslevelarray[0] as xx>
	        {
	        	<#assign PublicTransForms="PublicTransForm"+j+x><#list PublicTransForms?eval as result>         	    
	        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
	        	</#list> 
	        }<#if xx?is_last><#else>,</#if><#assign j=j+1>
		    </#list>
            ]
          }
        }}
      ]
   <#--     "HiredAuto": [
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
      ],-->
 <#--      "Garage": [
        {
          "GarageDetail": {
          <#assign GarageLevels="GarageLevel"+x><#list GarageLevels?eval as result>     
        	"${result.atrib}":"${result.value}",
        	</#list>
          "Forms": [<#assign j=1>
	        <#list 1..Garagelevelarray[0] as xx>
	        {
	        	<#assign GarageForms="GarageForm"+j+x><#list GarageForms?eval as result>         	    
	        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
	        	</#list> 
	        }<#if xx?is_last><#else>,</#if><#assign j=j+1>
		    </#list>
            ]
          }
        }
      ]-->
    }<#if x?is_last><#else>,</#if>
    </#list>  
  ]
}