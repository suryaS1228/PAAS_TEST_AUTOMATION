<#assign numofClassArray=[]><#list numofClass as x><#assign numofClassArray=numofClassArray+[x.value]></#list>
<#assign i=1>
<ACORD>
	<SignonRq>
		<SignonPswd>
			<CustId>
				<CustLoginId>starrcscadmin</CustLoginId>
			</CustId>
			<CustPswd>
				<Pswd>sKVSbX7wj8UYYAaar4amxQ==</Pswd>
			</CustPswd>
		</SignonPswd>
		<ClientDt>2012-09-14T11:03:46.073</ClientDt>
		<ClientApp>
			<Org>Starr</Org>
			<Name>LDCC</Name>
			<Version>1.00</Version>
		</ClientApp>
	</SignonRq>
	<InsuranceSvcRq>
		<#list Policy as result><#if result.atrib=="RqUID"><${result.atrib}>${result.value}</${result.atrib}></#if></#list>
		<com.csc_TransactionSeqNbr>1</com.csc_TransactionSeqNbr>
		<LargeDeductibleCreditCalculationRq>
			<MsgStatus>
				<MsgStatusCd>Success</MsgStatusCd>
				<MsgErrorCd>0</MsgErrorCd>
				<MsgStatusDesc>Success</MsgStatusDesc>
			</MsgStatus>
			<RqUID>27921299210669453589</RqUID>
			<TransactionRequestDt>2016/09/14</TransactionRequestDt>
			<TransactionEffectiveDt>2017/09/14</TransactionEffectiveDt>
			<Producer>
				<ItemIdInfo>
					<AgencyId>1800001</AgencyId>
				</ItemIdInfo>
				<ProducerInfo>
					<ContractNumber>starrunderwriter</ContractNumber>
				</ProducerInfo>
			</Producer>
			<InsuredOrPrincipal>
				<GeneralPartyInfo>
					<NameInfo>
						<CommlName>
							<#list Policy as result><#if result.atrib=="CommercialName"><${result.atrib}>${result.value}</${result.atrib}></#if></#list>
							<com.csc_ClientNumber></com.csc_ClientNumber>
						</CommlName>
					</NameInfo>
				</GeneralPartyInfo>
				<InsuredOrPrincipalInfo>
					<InsuredOrPrincipalRoleCd>NI</InsuredOrPrincipalRoleCd>
				</InsuredOrPrincipalInfo>
			</InsuredOrPrincipal>
			<CommlPolicy>
				<#list Policy as result><#if result.atrib=="com.csc_QuoteNumber"><${result.atrib}>${result.value}</${result.atrib}></#if></#list>
				<#list Policy as result><#if result.atrib=="PolicyNumber"><${result.atrib}>${result.value}</${result.atrib}></#if></#list>
				<PolicyVersion>1</PolicyVersion>
				<CompanyProductCd>WC</CompanyProductCd>
				<LOBCd>WCV</LOBCd>
				<ContractTerm>
					<#list Policy as result><#if result.atrib=="EffectiveDt"><${result.atrib}>${result.value}</${result.atrib}></#if></#list>
					<#list Policy as result><#if result.atrib=="ExpirationDt"><${result.atrib}>${result.value}</${result.atrib}></#if></#list>
				</ContractTerm>
				<PolicyStatusCd>N</PolicyStatusCd>
				<CurrentTermAmt>
					<#list Policy as result><#if result.atrib=="CurrentTermAmt"><Amt>${result.value}</Amt></#if></#list>
				</CurrentTermAmt>
				<#list Policy as result><#if result.atrib=="com.csc_TxnType"><${result.atrib}>${result.value}</${result.atrib}></#if></#list>
				<#list Policy as result><#if result.atrib=="com.csc_Submission_Nbr"><${result.atrib}>${result.value}</${result.atrib}></#if></#list>
				<CommlCoverage>
					<CoverageCd>WC</CoverageCd>
					<Deductible>
						<DeductibleTypeCd>00</DeductibleTypeCd>
						<DeductibleAppliesToCd>Amount</DeductibleAppliesToCd>
						<FormatCurrency>
							<#list DeductibleAmt as result><${result.atrib}>${result.value}</${result.atrib}></#list>
						</FormatCurrency>
					</Deductible>
					<Deductible>
						<DeductibleTypeCd>00</DeductibleTypeCd>
						<DeductibleAppliesToCd>Aggregate</DeductibleAppliesToCd>
						<FormatCurrency>
							<#list AggregateAmt as result><${result.atrib}>${result.value}</${result.atrib}></#list>
						</FormatCurrency>
					</Deductible>
				</CommlCoverage>
			</CommlPolicy>
			<WorkCompLineBusiness>
				<LOBCd>WC</LOBCd>
				<WorkCompRateState>
					<#list Policy as result><#if result.atrib=="StateProvCd"><${result.atrib}>${result.value}</${result.atrib}></#if></#list>
					<CreditOrSurcharge>
						<CreditSurchargeCd>EMOD</CreditSurchargeCd>
						<NumericValue>
							<#list EMODFormatModFactor as result><${result.atrib}>${result.value}</${result.atrib}></#list>
						</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
						<#list Policy as result><#if result.atrib=="CreditSurchargeCd"><${result.atrib}>${result.value}</${result.atrib}></#if></#list>
						<NumericValue>
							<FormatModFactor>0.500000</FormatModFactor>
						</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
						<CreditSurchargeCd>MRAP</CreditSurchargeCd>
						<NumericValue>
							<#list MRAPFormatModFactor as result><${result.atrib}>${result.value}</${result.atrib}></#list>
						</NumericValue>
					</CreditOrSurcharge>
					<#list 1..numofClassArray[0] as x> <#assign Classes="Class"+i>
					<WorkCompRateClass>
						<Exposure>200000</Exposure>
						<#list Classes?eval as result><${result.atrib}>${result.value}</${result.atrib}>
						</#list>
					</WorkCompRateClass><#assign i=i+1>
					</#list>
				</WorkCompRateState>		
					
			</WorkCompLineBusiness>
		</LargeDeductibleCreditCalculationRq>
	</InsuranceSvcRq>
</ACORD>
