<#assign i=0>
<#assign array=[]><#list NoOfState1 as x><#assign array=array+[x.value]></#list>
{
	<#-- <#assign InsuredLevels="InsuredLevel1"><#list InsuredLevels?eval as result>     
        "${result.atrib}":"${result.value}",
        </#list> -->
      "OwnerId": "35",
	  "ServiceRequestDetail": {
	    "BrowserIp": "111.93.119.74",
	    "OwnerId": "35",
	    "ResponseType": "JSON",
	    "ServiceRequestVersion": "1.0",
	    "Token": "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",
	    "UserName": "StarrCAAgent"
	  },
	   "EndClientUserUniqueSessionId": "Uniquesession",
	  "ProductNumber": "",
	  "ProductVerNumber": "STARR_CA_2018_V1",
	  "EventName": "StarrCA_GenerateDocument_1.1.0.1",
	  "PolicyNumber": "10111100101",
	  "EffectiveDate": "2019-01-22",
	  "InsuredName": "TestData1",
	  "IsPreviewNeeded": "No",
	  "ProducerName": "Producer Name",
	  "CompanyName": "Company Name",
	  "InsuredAddressLine2": "",
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
      ]
      <#-- "HiredAuto": [
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
      ]-->
    }<#if x?is_last><#else>,</#if>
    </#list>  
  ]
}