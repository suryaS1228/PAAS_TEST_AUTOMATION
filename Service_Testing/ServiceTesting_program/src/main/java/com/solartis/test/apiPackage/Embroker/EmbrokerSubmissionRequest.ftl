<#assign Numofcoverage=[]><#list Noofcoverage as x><#assign Numofcoverage=Numofcoverage+[x.value]></#list>
<#assign NumofQuestion=[]><#list NoofQuestion as y><#assign NumofQuestion=NumofQuestion+[y.value]></#list>
{   
"EndClientUserUniqueSessionId": "Uniquesession",
"OwnerId": "36",
"ServiceRequestDetail": 
{
<#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
},
"Policy":
{   
  "DataEncryption": true,
  "DoYouOutsourceAnyWorkPerformedForClientsToSubcontractors": true,
  "BreachOfPrivacyNotificationToCustomerClientOrEmployee": true,
  "ComputerNetworkOrSystemOrDataSecurityBreaches": true,
  "WrittenDemandForDamagesAsAResultOfBreachOfContractOrNegligence": true,
  "FutureClaim": true,
  "HasThereBeenOrDoYouAnticipateInTheNextYearABankruptcyProceeding": true,
  "DoYouAnticipateWithinTheNextYearASaleOfTheCompanyForLessThanTheAmountOfFundingRaised": true,
  "LiabilityClaimsOrViolationsOfLawsOrConsumerFraudOrLitigation": true,
  <#list Policy as result>"${result.atrib}":"${result.value}",</#list>
  "FundraiseList": [
  {
  "FundraiseDetail": 
  {
  <#list FundraiseDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
  }
  }
  ],
  "ThirdPartyDataList": [
  {
  <#list ThirdPartyDataList as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
  }
  ],
  "AdditionalQuestionsList": [
  <#assign i=1>
  <#list 1..NumofQuestion[0] as result>
  <#assign QuestionsInDetail="QuestionsInDetail"+i>
  {
  "QuestionsInDetail": {
  <#list QuestionsInDetail?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
  }}
  <#if result?is_last><#else>,</#if><#assign i=i+1>
  </#list>
]
}}