<#assign HoursWorkPerWeekarray=[]><#list HoursWorkPerWeek as x><#assign HoursWorkPerWeekarray=HoursWorkPerWeekarray+[x.value]></#list>
<#assign ProfessionalAssociationarray=[]><#list ProfessionalAssociation as x><#assign ProfessionalAssociationarray=ProfessionalAssociationarray+[x.value]></#list>
<#assign MiddleInitialarray=[]><#list MiddleInitial as x><#assign MiddleInitialarray=MiddleInitialarray+[x.value]></#list>
<#assign YearsInPracticearray=[]><#list YearsInPractice as x><#assign YearsInPracticearray=YearsInPracticearray+[x.value]></#list>
<#assign FirstNamearray=[]><#list FirstName as x><#assign FirstNamearray=FirstNamearray+[x.value]></#list>
<#assign ProviderEmailIdarray=[]><#list ProviderEmailId as x><#assign ProviderEmailIdarray=ProviderEmailIdarray+[x.value]></#list>
<#assign YearsOfPolicyarray=[]><#list YearsOfPolicy as x><#assign YearsOfPolicyarray=YearsOfPolicyarray+[x.value]></#list>
<#assign LastNamearray=[]><#list LastName as x><#assign LastNamearray=LastNamearray+[x.value]></#list>
<#assign ScheduleRatingFactorarray=[]><#list ScheduleRatingFactor as x><#assign ScheduleRatingFactorarray=ScheduleRatingFactorarray+[x.value]></#list>
<#assign ProviderClassarray=[]><#list ProviderClass as x><#assign ProviderClassarray=ProviderClassarray+[x.value]></#list>
<#assign numofProviderarray=[]><#list numofProvider as x><#assign numofProviderarray=numofProviderarray+[x.value]></#list>
<#assign MeetsStudentQualificationarray=[]><#list MeetsStudentQualification as x><#assign MeetsStudentQualificationarray=MeetsStudentQualificationarray+[x.value]></#list>

<#assign i=0>
{
  "Policy": {
  	"Provider": [
  	<#list 1..numofProviderarray[0] as result>
      {
      	"ProviderDetail":
      	 {
      	  "HoursWorkPerWeek": "${HoursWorkPerWeekarray[i]}",
          "ProfessionalAssociation": "${ProfessionalAssociationarray[i]}",
          "MiddleInitial": "${MiddleInitialarray[i]}",
          "YearsInPractice": "${YearsInPracticearray[i]}",
          "FirstName": "${FirstNamearray[i]}",
          "ProviderEmailId": "${ProviderEmailIdarray[i]}",
          "YearsOfPolicy": "${YearsOfPolicyarray[i]}",
          "LastName": "${LastNamearray[i]}",
          "ScheduleRatingFactor": "${ScheduleRatingFactorarray[i]}",
          "ProviderClass": "${ProviderClassarray[i]}",
          "MeetsStudentQualification": "${MeetsStudentQualificationarray[i]}",
          "UseofWrittenContractsFactor": "5",
          "TrainingorEducationFactor": "5",
          "ProfessionalReputationFactor": "5",
          "NatureofOperationsFactor": "5"
        }
      }
      <#assign i=i+1><#if i=numofProviderarray[0]><#else>,</#if>
      </#list>
],
     <#list Policy as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list> 
    },
  "OwnerId": "27",
  "ServiceRequestDetail": {
  <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
  },
  "EndClientUserUniqueSessionId": "Uniquesession"
}