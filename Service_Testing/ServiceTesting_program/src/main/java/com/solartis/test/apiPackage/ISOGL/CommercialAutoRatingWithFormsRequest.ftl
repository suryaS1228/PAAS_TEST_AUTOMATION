<#assign array=[]><#list NoOfState1 as x><#assign array=array+[x.value]></#list><#assign i=0>

{
  <#list Policy1 as result>"${result.atrib}":"${result.value}",</#list>
  "State": [
<#list 1..array[i] as x> 
<#assign PPT_Vehicle_Counts="PPT_Vehicle_Count"+x+"_1">										<#if PPT_Vehicle_Counts?eval??>             <#assign PPTArray=[]>			<#list PPT_Vehicle_Counts?eval as f> <#assign PPTArray=PPTArray+[f.value]></#list><#else></#if>
<#assign Special_Types_Vehicle_Counts="Special_Types_Vehicle_Count"+x+"_1">					<#if Special_Types_Vehicle_Counts?eval??>   <#assign SpecialArray=[]>		<#list Special_Types_Vehicle_Counts?eval  as f><#assign SpecialArray=SpecialArray+[f.value]></#list><#else></#if>
<#assign Public_Transportation_Vehicle_Counts="Public_Transportation_Vehicle_Count"+x+"_1">	<#if Public_Transportation_Vehicle_Counts?eval??> <#assign PublicArray=[]>  <#list Public_Transportation_Vehicle_Counts?eval as f><#assign PublicArray=PublicArray+[f.value]></#list><#else></#if>
<#assign Zone_Rated_Vehicle_Counts="Zone_Rated_Vehicle_Count"+x+"_1">                       <#if Zone_Rated_Vehicle_Counts?eval??>   	<#assign ZoneArray=[]> 			<#list Zone_Rated_Vehicle_Counts ?eval  as f><#assign ZoneArray=ZoneArray+[f.value]></#list><#else></#if>
<#assign Truck_Vehicle_Counts="Truck_Vehicle_Count"+x+"_1">                                 <#if Truck_Vehicle_Counts?eval??>        	<#assign TruckArray=[]> 		<#list Truck_Vehicle_Counts ?eval  as f><#assign TruckArray=TruckArray+[f.value]></#list></#if>
    {
      <#assign States="State"+x><#list States?eval as result>"${result.atrib}":"${result.value}",</#list> 
      "AddedPersonalInjuryProtection": [
        {
          "AddedPersonalInjuryProtectionDetail": 
          {
            "FamilyMembersName": "1",
            <#assign AddedPersonalInjuryProtectionDetails="AddedPersonalInjuryProtectionDetail"+x><#list AddedPersonalInjuryProtectionDetails?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
          }
        }
      ],	
      "BusinessInterruptionCoverage": [
        {
          <#assign BusinessInterruptionCoverages="BusinessInterruptionCoverage"+x><#list BusinessInterruptionCoverages?eval as result>"${result.atrib}":"${result.value}",</#list> 
         
          "BusinessInterruptionCoverageDetail": [
            {
            <#assign BusinessInterruptionCoverageDetails="BusinessInterruptionCoverageDetail"+x><#list BusinessInterruptionCoverageDetails?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list> 
            
            }
          ]
        }
      ],
      "TrailerInterchangeAgreement": [
        {
          "TrailerInterchangeAgreementDetail": [
            {
             <#assign TrailerInterchangeAgreementDetails="TrailerInterchangeAgreementDetail"+x><#list TrailerInterchangeAgreementDetails?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list> 
             
            }
          ],
          <#assign TrailerInterchangeAgreementDetailss="TrailerInterchangeAgreementDetail1"+x><#list TrailerInterchangeAgreementDetailss?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list> 
        }
      ],
      "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverage": [
        {
          "CommercialAutoNamedIndividualsBroadenedPersonalInjuryProtectionCoverageDetail": [
            {
             <#assign NamedIndividualsBroadenedPersonalInjuryProtectionCoverageDetails="NamedIndividualsBroadenedPersonalInjuryProtectionCoverageDetail"+x><#list NamedIndividualsBroadenedPersonalInjuryProtectionCoverageDetails?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list> 
            
            }
          ],
          <#assign NamedIndividualsBroadenedPersonalInjuryProtectionCoverageDetailss="NamedIndividualsBroadenedPersonalInjuryProtectionCoverageDetail1"+x><#list NamedIndividualsBroadenedPersonalInjuryProtectionCoverageDetailss?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list> 
        }
      ],
      "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividuals": [
        {
          "CommercialAutoDriveOtherCarCovBroadenedCovForNamedIndividualsDetail": [
            {
             <#assign DriveOtherCarCovBroadenedCovForNamedIndividualsDetails="DriveOtherCarCovBroadenedCovForNamedIndividualsDetail"+x><#list DriveOtherCarCovBroadenedCovForNamedIndividualsDetails?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list> 
             
            }
          ],  
          <#assign DriveOtherCarCovBroadenedCovForNamedIndividualsDetail1s="DriveOtherCarCovBroadenedCovForNamedIndividualsDetail1"+x><#list DriveOtherCarCovBroadenedCovForNamedIndividualsDetail1s?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>   
          
        }
      ],
      "CommercialAutoPennsylvaniaNamedIndividualsBroadenedFirstPartyBenefits": [
        {
          "CommercialAutoPennsylvaniaNamedIndividualsBroadenedFirstPartyBenefitsDetail": 
          {
           <#assign PennsylvaniaNamedIndividualsBroadenedFirstPartyBenefitsDetails="PennsylvaniaNamedIndividualsBroadenedFirstPartyBenefitsDetail"+x><#list PennsylvaniaNamedIndividualsBroadenedFirstPartyBenefitsDetails?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list> 
            
          }
        }
      ],
      "CommercialAutoGaragekeepersCoverage": [
        { 
         <#assign CommercialAutoGaragekeepersCoverages="CommercialAutoGaragekeepersCoverage"+x><#list CommercialAutoGaragekeepersCoverages?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else><#if result?is_last><#else>,</#if></#if></#list>  
         
        }
      ],
      "Garage": [
        { 
        "GarageDetail": 
        {
         <#assign CommercialAutoGarages="CommercialAutoGarage"+x><#list CommercialAutoGarages?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else><#if result?is_last><#else>,</#if></#if></#list>  
         
        }
        }
      ],
      "CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipment": [
        {
        <#assign CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipments="CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipment"+x><#list CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipments?eval as result>"${result.atrib}":"${result.value}",</#list> 
       
          "CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipmentDetail": [
            {
            <#assign CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipmentDetails="CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipmentDetail"+x><#list CommercialAutoGaragekeepersCovCustomersSoundReceivingEquipmentDetails?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list> 
           
            }
          ]
        }
      ],
      "Truck": 
      [
      <#if Truck_Vehicle_Counts?eval??>
      <#list 1..TruckArray[i] as y> 
      {
       "TruckDetail": {
         "AudioVisualAndDataElectronicEquipmentCoverageList": 
         [
           {
           <#assign Truck_AudioVisualAndDataElectronicEquipmentCoverageLists="Truck_AudioVisualAndDataElectronicEquipmentCoverageList"+x+"_"+y><#list Truck_AudioVisualAndDataElectronicEquipmentCoverageLists?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list> 
           }
         ],
         "CommercialAutoAutoLoanLeaseGapCovList": 
         [
          {
           <#assign Truck_CommercialAutoAutoLoanLeaseGapCovLists="Truck_CommercialAutoAutoLoanLeaseGapCovList"+x+"_"+y><#list Truck_CommercialAutoAutoLoanLeaseGapCovLists?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
          }
         ],
         "CommercialAutoAdditionalPersonalInjuryProtectionNewYorkList": [
           {
           <#assign Truck_AdditionalPersonalInjuryProtectionNewYorkLists="Truck_AdditionalPersonalInjuryProtectionNewYorkList"+x+"_"+y><#list Truck_AdditionalPersonalInjuryProtectionNewYorkLists?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list> 
           }
         ],
         "CommercialAutoRentalReimbursementCovList": [
           {
           <#assign Truck_CommercialAutoRentalReimbursementCovLists="Truck_CommercialAutoRentalReimbursementCovList"+x+"_"+y><#list Truck_CommercialAutoRentalReimbursementCovLists?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
           }
         ],
         <#assign TruckDetailss="TruckDetails"+x+"_"+y><#list TruckDetailss?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }
      }
      <#if y?is_last><#else>,</#if>
      </#list> 
      <#else> </#if>
      ],
      "PrivatePassenger": 
      [
      <#if PPT_Vehicle_Counts?eval??>
      <#list 1..PPTArray[i] as z> 
      {
        "PrivatePassengerDetail": 
         {
         "AudioVisualAndDataElectronicEquipmentCoverageList": 
           [
            {
             <#assign PPT_AudioVisualAndDataElectronicEquipmentCoverageLists="PPT_AudioVisualAndDataElectronicEquipmentCoverageList"+x+"_"+z><#list PPT_AudioVisualAndDataElectronicEquipmentCoverageLists?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list> 
             
            }
           ],
           "CommercialAutoAutoLoanLeaseGapCovList": 
           [
          {
           <#assign PPT_CommercialAutoAutoLoanLeaseGapCovLists="PPT_CommercialAutoAutoLoanLeaseGapCovList"+x+"_"+z><#list PPT_CommercialAutoAutoLoanLeaseGapCovLists?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
          }
         ],
           "CommercialAutoWashingtonAutoLoanCoverageGapCovList": 
           [
            {
             <#assign CommercialAutoWashingtonAutoLoanCoverageGapCovLists="CommercialAutoWashingtonAutoLoanCoverageGapCovList"+x+"_"+z><#list CommercialAutoWashingtonAutoLoanCoverageGapCovLists?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
            }
           ],
        "CommercialAutoAdditionalPersonalInjuryProtectionNewYorkList": 
          [
           {
            <#assign PPT_CommercialAutoAdditionalPersonalInjuryProtectionNewYorkLists="PPT_CommercialAutoAdditionalPersonalInjuryProtectionNewYorkList"+x+"_"+z><#list PPT_CommercialAutoAdditionalPersonalInjuryProtectionNewYorkLists?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list> 
           }
          ],
        "CommercialAutoRentalReimbursementCovList": 
         [
          {
           <#assign PPT_CommercialAutoRentalReimbursementCovLists="PPT_CommercialAutoRentalReimbursementCovList"+x+"_"+z><#list PPT_CommercialAutoRentalReimbursementCovLists?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list> 
               
          }
          ],
          <#assign PPTDetailss="PPTDetails"+x+"_"+z><#list PPTDetailss?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else><#if result?is_last><#else>,</#if></#if></#list> 
          
          }
          }
          <#if z?is_last><#else>,</#if>
          </#list> 
          <#else></#if>
      ],
      "PublicTransportation": 
      [
      <#if Public_Transportation_Vehicle_Counts?eval??>
      <#list 1..PublicArray[i] as a> 
        {
          "PublicTransportationDetail": {
             "AudioVisualAndDataElectronicEquipmentCoverageList": 
             [
                {
              <#assign Public_AudioVisualAndDataElectronicEquipmentCoverageLists="Public_AudioVisualAndDataElectronicEquipmentCoverageList"+x+"_"+a><#list Public_AudioVisualAndDataElectronicEquipmentCoverageLists?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list> 
                }
             ],
             "CommercialAutoAutoLoanLeaseGapCovList": 
           [
          {
           <#assign Public_CommercialAutoAutoLoanLeaseGapCovLists="Public_CommercialAutoAutoLoanLeaseGapCovList"+x+"_"+a><#list Public_CommercialAutoAutoLoanLeaseGapCovLists?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
          }
         ],
             "CommercialAutoAdditionalPersonalInjuryProtectionNewYorkList": 
             [
                {
              <#assign Public_AdditionalPersonalInjuryProtectionNewYorkLists="Public_AdditionalPersonalInjuryProtectionNewYorkList"+x+"_"+a><#list Public_AdditionalPersonalInjuryProtectionNewYorkLists?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list> 
                }
             ],
            "CommercialAutoRentalReimbursementCovList": 
            [
               {
        
             <#assign Public_CommercialAutoRentalReimbursementCovLists="Public_CommercialAutoRentalReimbursementCovList"+x+"_"+a><#list Public_CommercialAutoRentalReimbursementCovLists?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
               }
            ],
          
          <#assign PublicDetailss="PublicDetails"+x+"_"+a><#list PublicDetailss?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }
      }
      <#if a?is_last><#else>,</#if>
      </#list>
      <#else> </#if>
      ],
      "ZoneRated": 
      [
      <#if Zone_Rated_Vehicle_Counts?eval??>
      <#list 1..ZoneArray[i] as b> 
      {
       "ZoneRatedDetail": {
         "AudioVisualAndDataElectronicEquipmentCoverageList": 
         [
           {
           <#assign Zone_AudioVisualAndDataElectronicEquipmentCoverageLists="Zone_AudioVisualAndDataElectronicEquipmentCoverageList"+x+"_"+b><#list Zone_AudioVisualAndDataElectronicEquipmentCoverageLists?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list> 
           }
         ],
         "CommercialAutoAutoLoanLeaseGapCovList": 
           [
          {
           <#assign Zone_CommercialAutoAutoLoanLeaseGapCovLists="Zone_CommercialAutoAutoLoanLeaseGapCovList"+x+"_"+b><#list Zone_CommercialAutoAutoLoanLeaseGapCovLists?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
          }
         ],
         "CommercialAutoAdditionalPersonalInjuryProtectionNewYorkList": [
           {
           <#assign Zone_AdditionalPersonalInjuryProtectionNewYorkLists="Zone_AdditionalPersonalInjuryProtectionNewYorkList"+x+"_"+b><#list Zone_AdditionalPersonalInjuryProtectionNewYorkLists?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list> 
           }
         ],
         "CommercialAutoRentalReimbursementCovList": [
           {
           <#assign Zone_CommercialAutoRentalReimbursementCovLists="Zone_CommercialAutoRentalReimbursementCovList"+x+"_"+b><#list Zone_CommercialAutoRentalReimbursementCovLists?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
           }
         ],
         <#assign ZoneDetailss="ZoneDetails"+x+"_"+b><#list ZoneDetailss?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }
      }
      <#if b?is_last><#else>,</#if>
      </#list>
      <#else> </#if>
      ],
      "Special": 
      [
      <#if Special_Types_Vehicle_Counts?eval??>
      <#list 1..SpecialArray[i] as c> 
      {
       "SpecialDetail": {
         "AudioVisualAndDataElectronicEquipmentCoverageList": 
         [
           {
           <#assign Special_AudioVisualAndDataElectronicEquipmentCoverageLists="Special_AudioVisualAndDataElectronicEquipmentCoverageList"+x+"_"+c><#list Special_AudioVisualAndDataElectronicEquipmentCoverageLists?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list> 
           }
         ],
         "CommercialAutoAutoLoanLeaseGapCovList": 
           [
          {
           <#assign Special_Types_CommercialAutoAutoLoanLeaseGapCovLists="Special_Types_CommercialAutoAutoLoanLeaseGapCovList"+x+"_"+c><#list Special_Types_CommercialAutoAutoLoanLeaseGapCovLists?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
          }
         ],
         "CommercialAutoAdditionalPersonalInjuryProtectionNewYorkList": [
           {
           <#assign Special_CommercialAutoAdditionalPersonalInjuryProtectionNewYorkLists="Special_CommercialAutoAdditionalPersonalInjuryProtectionNewYorkList"+x+"_"+c><#list Special_CommercialAutoAdditionalPersonalInjuryProtectionNewYorkLists?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list> 
           }
         ],
         "CommercialAutoRentalReimbursementCovList": [
           {
           <#assign Special_CommercialAutoRentalReimbursementCovLists="Special_CommercialAutoRentalReimbursementCovList"+x+"_"+c><#list Special_CommercialAutoRentalReimbursementCovLists?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
           }
         ],
         <#assign SpecialTypesDetailss="SpecialTypesDetails"+x+"_"+c><#list SpecialTypesDetailss?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }
      }   
      <#if c?is_last><#else>,</#if>
      </#list> 
      <#else> </#if>
      ],
      "HiredAuto": [
        {
          "HiredAutoDetail": 
          {
           <#assign HiredAutoDetails="HiredAutoDetail"+x><#list HiredAutoDetails?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list> 
            
          }
        }
      ],
      "NonOwnedAuto": [
        {
          "NonOwnedAutoDetail": 
          {
           <#assign NonOwnedAutoDetails="NonOwnedAutoDetail"+x><#list NonOwnedAutoDetails?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list> 
          }
        }
      ]
    }
<#if x?is_last><#else>,</#if>
</#list> 
  ],
  "ServiceRequestDetail": 
  {
   <#list ServiceRequestDetail1 as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list> 
  }
}