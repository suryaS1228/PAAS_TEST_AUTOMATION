<#assign i=0>
<#assign array=[]><#list NoOfState1 as x><#assign array=array+[x.value]></#list>
{
	<#assign InsuredLevels="InsuredLevel1"><#list InsuredLevels?eval as result>     
        "${result.atrib}":"${result.value}",
        </#list> 
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
    
      "Forms": [<#assign j=j+1>
        <#list 1..policylevelarray[0] as xx>
        {
        	<#assign PolicyForms="PolicyForm"+j+x><#list PolicyForms?eval as result>         	    
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list> 
        }<#if xx?is_last><#else>,</#if><#assign j=j+1>
	    </#list>        
      ],
      
      "CommercialAutoTruckersExcessCoverageForNamedInsuredAndNamedLessorsForLeasedAuto": [
        {
          "CommercialAutoTruckersExcessCoverageForNamedInsuredAndNamedLessorsForLeasedAutoDetail": {
            <#assign TruckersExcessCoverageForNamedInsuredAndNamedLessorsForLeasedAutoDetails="TruckersExcessCoverageForNamedInsuredAndNamedLessorsForLeasedAutoDetail"+x><#list TruckersExcessCoverageForNamedInsuredAndNamedLessorsForLeasedAutoDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
          }
        }
      ],
      "CommercialAutoColoradoChangesDriverExclusion": [
        {
          "CommercialAutoColoradoChangesDriverExclusionDetail": {
            <#assign ColoradoChangesDriverExclusionDetails="ColoradoChangesDriverExclusionDetail"+x><#list ColoradoChangesDriverExclusionDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
          }
        }
      ],
      "CommercialAutoOfficialInspectionStations": [
        {
          "CommercialAutoOfficialInspectionStationsDetail": {
            <#assign OfficialInspectionStationsDetails="OfficialInspectionStationsDetail"+x><#list OfficialInspectionStationsDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
          }
        }
      ],
      "CommercialAutoCancellationNonrenewalNoticeToDesignatedPersonOrganization": [
        {
          "CommercialAutoCancellationNonrenewalNoticeToDesignatedPersonOrganizationDetail": {
            <#assign CancellationNonrenewalNoticeToDesignatedPersonOrganizationDetails="CancellationNonrenewalNoticeToDesignatedPersonOrganizationDetail"+x><#list CancellationNonrenewalNoticeToDesignatedPersonOrganizationDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
          }
        }
      ],
      "CommercialAutoTruckersNamedLesseeAsInsured": [
        {
          "CommercialAutoTruckersNamedLesseeAsInsuredDetail": {
           <#assign TruckersNamedLesseeAsInsuredDetails="TruckersNamedLesseeAsInsuredDetail"+x><#list TruckersNamedLesseeAsInsuredDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
          }
        }
      ],
      "CommercialAutoCoverageForCertainOperationsInConnectionWithRailroads": [
        {
          "CommercialAutoCoverageForCertainOperationsInConnectionWithRailroadsDetail": {
            <#assign CoverageForCertainOperationsInConnectionWithRailroadsDetails="CoverageForCertainOperationsInConnectionWithRailroadsDetail"+x><#list CoverageForCertainOperationsInConnectionWithRailroadsDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
          }
        }
      ],
      "CommercialAutoWaiverOfTransferOfRightsOfRecoveryAgainstOthersToUs": [
        {
          "CommercialAutoWaiverOfTransferOfRightsOfRecoveryAgainstOthersToUsDetail": {
            <#assign WaiverOfTransferOfRightsOfRecoveryAgainstOthersToUsDetails="WaiverOfTransferOfRightsOfRecoveryAgainstOthersToUsDetail"+x><#list WaiverOfTransferOfRightsOfRecoveryAgainstOthersToUsDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
          }
        }
      ],
      "CommercialAutoFellowEmployeeCoverageForDesignatedEmployeesPositions": [
        {
          "CommercialAutoFellowEmployeeCoverageForDesignatedEmployeesPositionsDetail": {
           <#assign FellowEmployeeCoverageForDesignatedEmployeesPositionsDetails="FellowEmployeeCoverageForDesignatedEmployeesPositionsDetail"+x><#list FellowEmployeeCoverageForDesignatedEmployeesPositionsDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
          }
        }
      ],
      "CommercialAutoOptionalBenefitsCoverage": [
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
      ],
      
      "Truck": [
        {
          "TruckDetail": {
          	<#assign TruckLevels="TruckLevel"+x><#list TruckLevels?eval as result>     
        	"${result.atrib}":"${result.value}",
        	</#list> 
           "CommercialAutoAdditionalPersonalInjuryProtectionNewYorkList": [
              {        
                	<#assign AdditionalPersonalInjuryProtectionNewYorkLists="TruckAdditionalPersonalInjuryProtectionNewYorkList"+x><#list AdditionalPersonalInjuryProtectionNewYorkLists?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list> 
              }
              
            ],
            "CommercialAutoAdditionalInsuredAndLossPayee": {
              "CommercialAutoAdditionalInsuredAndLossPayeeDetail": [
              {
                	<#assign AdditionalInsuredAndLossPayeeDetails="TruckAdditionalInsuredAndLossPayeeDetail"+x><#list AdditionalInsuredAndLossPayeeDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list> 
              }
              ]
            },
            "CommercialAutoLessorAdditionalInsuredAndLossPayee": {
              "CommercialAutoLessorAdditionalInsuredAndLossPayeeDetail": [
              {
                	<#assign LessorAdditionalInsuredAndLossPayeeDetails="TruckLessorAdditionalInsuredAndLossPayeeDetail"+x><#list LessorAdditionalInsuredAndLossPayeeDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list> 
                 }
              ]
            },
            "commercialAutoMultiPurposeEquipment": {
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
            },
            "Forms": [<#assign j=1>
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
           
            "CommercialAutoAdditionalInsuredAndLossPayee": {
              "CommercialAutoAdditionalInsuredAndLossPayeeDetail": [
                {
                	<#assign AdditionalInsuredAndLossPayeeDetails="SpecialAdditionalInsuredAndLossPayeeDetail"+x><#list AdditionalInsuredAndLossPayeeDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                 }
              ]
            },
            
            "CommercialAutoLessorAdditionalInsuredAndLossPayee": {
              "CommercialAutoLessorAdditionalInsuredAndLossPayeeDetail": [
                {
                	<#assign LessorAdditionalInsuredAndLossPayeeDetails="SpecialLessorAdditionalInsuredAndLossPayeeDetail"+x><#list LessorAdditionalInsuredAndLossPayeeDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                 }
              ]
            },
            
           "commercialAutoMultiPurposeEquipment": {
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
            },
            "Forms": [<#assign j=1>
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
            "CommercialAutoAdditionalInsuredAndLossPayee": {
              "CommercialAutoAdditionalInsuredAndLossPayeeDetail": [
              {
                	<#assign AdditionalInsuredAndLossPayeeDetails="PrivateAdditionalInsuredAndLossPayeeDetail"+x><#list AdditionalInsuredAndLossPayeeDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                 }
               ]
            },
            "CommercialAutoLessorAdditionalInsuredAndLossPayee": {
              "CommercialAutoLessorAdditionalInsuredAndLossPayeeDetail": [
              {
                	<#assign LessorAdditionalInsuredAndLossPayeeDetails="PrivateLessorAdditionalInsuredAndLossPayeeDetail"+x><#list LessorAdditionalInsuredAndLossPayeeDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                 }
               ]
            },
            "commercialAutoMultiPurposeEquipment": {
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
            },
            "Forms": [<#assign j=1>
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
            "CommercialAutoAdditionalInsuredAndLossPayee": {
              "CommercialAutoAdditionalInsuredAndLossPayeeDetail": [
              {
                	<#assign AdditionalInsuredAndLossPayeeDetails="ZoneRatedAdditionalInsuredAndLossPayeeDetail"+x><#list AdditionalInsuredAndLossPayeeDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                }
              ]
            },
            "CommercialAutoLessorAdditionalInsuredAndLossPayee": {
              "CommercialAutoLessorAdditionalInsuredAndLossPayeeDetail": [
              {
                	<#assign LessorAdditionalInsuredAndLossPayeeDetails="ZoneRatedLessorAdditionalInsuredAndLossPayeeDetail"+x><#list LessorAdditionalInsuredAndLossPayeeDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                }
              ]
            },
            "commercialAutoMultiPurposeEquipment": {
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
            },
            "Forms": [<#assign j=1>
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
            "CommercialAutoAdditionalInsuredAndLossPayee": {
              "CommercialAutoAdditionalInsuredAndLossPayeeDetail": [
              {
                	<#assign AdditionalInsuredAndLossPayeeDetails="PublicTransAdditionalInsuredAndLossPayeeDetail"+x><#list AdditionalInsuredAndLossPayeeDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                }
              ]
            },
            "CommercialAutoLessorAdditionalInsuredAndLossPayee": {
              "CommercialAutoLessorAdditionalInsuredAndLossPayeeDetail": [
              {
                	<#assign LessorAdditionalInsuredAndLossPayeeDetails="PublicTransLessorAdditionalInsuredAndLossPayeeDetail"+x><#list LessorAdditionalInsuredAndLossPayeeDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                }
              ]
            },
            "commercialAutoMultiPurposeEquipment": {
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
            },
            "Forms": [<#assign j=1>
	        <#list 1..PublicTranslevelarray[0] as xx>
	        {
	        	<#assign PublicTransForms="PublicTransForm"+j+x><#list PublicTransForms?eval as result>         	    
	        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
	        	</#list> 
	        }<#if xx?is_last><#else>,</#if><#assign j=j+1>
		    </#list>
            ]
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
      ],
      "Garage": [
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
      ]
    }<#if x?is_last><#else>,</#if>
    </#list>  
  ]
}