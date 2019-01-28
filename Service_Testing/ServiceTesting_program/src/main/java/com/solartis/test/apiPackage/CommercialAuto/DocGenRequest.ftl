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
      "Forms": [<#assign j=j+1>
        <#list 1..policylevelarray[0] as xx>
        {
        	<#assign PolicyForms="PolicyForm"+j+x><#list PolicyForms?eval as result>         	    
        	"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        	</#list> 
        }<#if xx?is_last><#else>,</#if><#assign j=j+1>
	    </#list>        
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