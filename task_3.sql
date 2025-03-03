SELECT ml.id AS loan_id,
       COALESCE(SUM(rs.principal_amount + rs.interest_amount + rs.fee_charges_amount + rs.penalty_charges_amount), 0) AS scheduled_amount,
       COALESCE(SUM(tr.amount), 0) AS paid_amount,
       (COALESCE(SUM(rs.principal_amount + rs.interest_amount + rs.fee_charges_amount + rs.penalty_charges_amount), 0) - 
        COALESCE(SUM(tr.amount), 0)) AS current_balance
FROM `loan-schema`.`m_loan` ml
LEFT JOIN `loan-schema`.`m_loan_repayment_schedule` rs ON ml.id = rs.loan_id AND rs.duedate <= CURDATE()
LEFT JOIN `loan-schema`.`m_loan_transaction` tr ON ml.id = tr.loan_id
GROUP BY ml.id;