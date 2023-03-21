-- ## 595. 大的国家 ##
select name ,population,area
from World
where area >=3000000 or population >=25000000;

-- 175. 组合两个表
select  FirstName ,LastName , City , State
from Person p left join Address a on p.PersonId = a.PersonId

