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

-- 1683. 无效的推文
select tweet_id
from Tweets
where CHAR_LENGTH(content) >15 ;

-- 1378. 使用唯一标识码替换员工ID
select unique_id , name
from Employees e left join EmployeeUNI u on e.id = u.id

-- 1068. 产品销售分析 I
select product_name , 'year', price
from  Sales s left join  Products p on s.product_id = p.product_id

-- 1661. 每台机器的进程平均运行时间
select machine_id ,
       round( sum( case when activity_type = 'start' then -timestamp
                when activity_type = 'end' then timestamp else null end
           )/ count( distinct process_id)
        , 3 ) as processing_time
from Activity
group by machine_id ;

select machine_id ,round(avg(time) ,3) as processing_time
from (
    select machine_id , max(timestamp ) - min(timestamp ) as time
    from Activity
    group by machine_id , process_id
     ) a
group by  machine_id ;





