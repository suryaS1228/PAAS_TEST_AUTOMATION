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
    "CommercialAutoBusinessInterruptionCoverage": [
        {
          "CommercialAutoBusinessInterruptionCoverageDetail": {
          	<#assign BusinessInterruptionCoverageDetails="BusinessInterruptionCoverageDetail"+x><#list BusinessInterruptionCoverageDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
           }
        }
      ],
      "CommercialAutoAddedPersonalInjuryProtection": [
        {
          "CommercialAutoAddedPersonalInjuryProtectionDetail": {
          	<#assign AddedPersonalInjuryProtectionDetails="AddedPersonalInjuryProtectionDetail"+x><#list AddedPersonalInjuryProtectionDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
          }
        }
      ],
      "CommercialAutoAdditionalInsured": [
        {
          "CommercialAutoAdditionalInsuredDetail": {
          	<#assign AdditionalInsuredDetails="AdditionalInsuredDetail"+x><#list AdditionalInsuredDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
           }
        }
      ],
       "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividuals": [
        {
          "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsDetail": {
          	<#assign DriveOtherCarCovBroadenedCovForNamedIndividualsDetails="DriveOtherCarCovBroadenedCovForNamedIndividualsDetail"+x><#list DriveOtherCarCovBroadenedCovForNamedIndividualsDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
          }
        }
      ],
       "CommercialAutoNamedDriverCollisionCoverage": [
        {
          "CommercialAutoNamedDriverCollisionCoverageDetail": {
          	<#assign NamedDriverCollisionCoverageDetails="NamedDriverCollisionCoverageDetail"+x><#list NamedDriverCollisionCoverageDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
          }
        }
      ],
      "CommercialAutoDesignatedInsuredForCoveredAutosLiabilityCoverage": [
        {
          "CommercialAutoDesignatedInsuredForCoveredAutosLiabilityCoverageDetail": {
          	<#assign DesignatedInsuredForCoveredAutosLiabilityCoverageDetails="DesignatedInsuredForCoveredAutosLiabilityCoverageDetail"+x><#list DesignatedInsuredForCoveredAutosLiabilityCoverageDetails?eval as result>     
        "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
           }
        }
      ],
      "Forms": [
        {
        	
        }
      ],
      "Truck": [
        {
          "TruckDetail": {
          	<#assign TruckLevels="TruckLevel"+x><#list TruckLevels?eval as result>     
        	"${result.atrib}":"${result.value}",
        	</#list> 
           "CommercialAutoWashingtonLossPayableFormReg335": [
              {
                "CommercialAutoWashingtonLossPayableFormReg335Detail": {
                	<#assign WashingtonLossPayableFormReg335Details="TruckWashingtonLossPayableFormReg335Detail"+x><#list WashingtonLossPayableFormReg335Details?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list> 
                }
              }
            ],
            "CommercialAutoLessorAdditionalInsuredAndLossPayee": [
              {
                "CommercialAutoLessorAdditionalInsuredAndLossPayeeDetail": {
                	<#assign LessorAdditionalInsuredAndLossPayeeDetails="TruckLessorAdditionalInsuredAndLossPayeeDetail"+x><#list LessorAdditionalInsuredAndLossPayeeDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list> 
                 }
              }
            ],
            "Forms": [
              {
              
              }
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
          "CommercialAutoWashingtonLossPayableFormReg335": [
              {
                "CommercialAutoWashingtonLossPayableFormReg335Detail": {
                <#assign WashingtonLossPayableFormReg335Details="SpecialWashingtonLossPayableFormReg335Detail"+x><#list WashingtonLossPayableFormReg335Details?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                }
              }
            ],
            "CommercialAutoLessorAdditionalInsuredAndLossPayee": [
              {
                "CommercialAutoLessorAdditionalInsuredAndLossPayeeDetail": {
                	<#assign LessorAdditionalInsuredAndLossPayeeDetails="SpecialLessorAdditionalInsuredAndLossPayeeDetail"+x><#list LessorAdditionalInsuredAndLossPayeeDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                 }
              }
            ],
            "Forms": [
              {
              
              }
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
          "CommercialAutoWashingtonLossPayableFormReg335": [
              {
                "CommercialAutoWashingtonLossPayableFormReg335Detail": {
                	<#assign WashingtonLossPayableFormReg335Details="PrivateWashingtonLossPayableFormReg335Detail"+x><#list WashingtonLossPayableFormReg335Details?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                 }
              }
            ],
            "CommercialAutoLessorAdditionalInsuredAndLossPayee": [
              {
                "CommercialAutoLessorAdditionalInsuredAndLossPayeeDetail": {
                	<#assign LessorAdditionalInsuredAndLossPayeeDetails="PrivateLessorAdditionalInsuredAndLossPayeeDetail"+x><#list LessorAdditionalInsuredAndLossPayeeDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                 }
              }
            ],
            "Forms": [
              {
              
              }
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
          "CommercialAutoWashingtonLossPayableFormReg335": [
              {
                "CommercialAutoWashingtonLossPayableFormReg335Detail": {
                	<#assign oWashingtonLossPayableFormReg335s="ZoneRatedWashingtonLossPayableFormReg335"+x><#list oWashingtonLossPayableFormReg335s?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                 }
              }
            ],
            "CommercialAutoLessorAdditionalInsuredAndLossPayee": [
              {
                "CommercialAutoLessorAdditionalInsuredAndLossPayeeDetail": {
                	<#assign LessorAdditionalInsuredAndLossPayeeDetails="ZoneRatedLessorAdditionalInsuredAndLossPayeeDetail"+x><#list LessorAdditionalInsuredAndLossPayeeDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                }
              }
            ],
            "Forms": [
              {
              
              }
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
           "CommercialAutoWashingtonLossPayableFormReg335": [
              {
                "CommercialAutoWashingtonLossPayableFormReg335Detail": {
                	<#assign WashingtonLossPayableFormReg335Details="PublicWashingtonLossPayableFormReg335Detail"+x><#list WashingtonLossPayableFormReg335Details?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                 }
              }
            ],
            "CommercialAutoLessorAdditionalInsuredAndLossPayee": [
              {
                "CommercialAutoLessorAdditionalInsuredAndLossPayeeDetail": {
                	<#assign LessorAdditionalInsuredAndLossPayeeDetails="PublicLessorAdditionalInsuredAndLossPayeeDetail"+x><#list LessorAdditionalInsuredAndLossPayeeDetails?eval as result>     
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list>
                 }
              }
            ],
            "Forms": [
              {
              
               }
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
          "Forms": [
              {
              
              }
            ]
          }
        }
      ]
    }<#if x?is_last><#else>,</#if>
    </#list>  
  ]
}