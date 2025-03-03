SELECT ml.id AS loan_id, 
       rs.principal_amount + rs.interest_amount AS weekly_payment
FROM `loan-schema`.`m_loan` ml
JOIN `loan-schema`.`m_loan_repayment_schedule` rs ON ml.id = rs.loan_id
WHERE rs.completed_derived = 0
ORDER BY rs.duedate
LIMIT 1;