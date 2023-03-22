-- ## 595. 大的国家 ##
select name ,population,area
from World
where area >=3000000 or population >=25000000;

-- 175. 组合两个表
select  FirstName ,LastName , City , State
from Person p left join Address a on p.PersonId = a.PersonId

-- 1757. 可回收且低脂的产品
select product_id
from Products
where low_fats = 'Y' and recyclable = 'Y' ;

-- 176. 第二高的薪水
select  (
select salary
from Employee
group by salary
order by salary desc limit 1 offset 1  ) as SecondHighestSalary
-- limit 1 ,1

-- 183. 从不订购的客户
select Name as Customers
from Customers
where Id not in  (select CustomerId from Orders )

select c.Name as Customers
from Customers as c
     left join Orders as o on c.Id = o.CustomerId
where o.Id is null

-- 178. 分数排名
select a.score as score ,
       (select count(distinct b.score)  from  Scores b where b.score>=a.score) as 'rank'
    from Scores a
order by a.score desc;

-- rank() 并列后的是间隔排名 dense_rank() 并列后的是连续  row_number() 列号
select score , dense_rank() over(order by score desc) as 'rank'
from Scores  ;
-- 需要MYSQL 8 版本
