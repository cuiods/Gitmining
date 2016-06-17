
%connect to database
conn =database('gitmining','cuiods','0628','com.mysql.jdbc.Driver','jdbc:mysql://139.129.48.182:3306/gitmining');
query = exec(conn,'SELECT SUM(U.followers),AVG(U.followers),Repo.star_count FROM (SELECT * FROM sec_repo where create_at < "2013-12-30") AS Repo LEFT JOIN sec_contributor AS Contri ON (Repo.owner = Contri.repo_owner AND Repo.name = Contri.repo_name) LEFT JOIN sec_user AS U ON U.login = Contri.contributor GROUP BY Repo.owner,Repo.name ');  
cursor = fetch(query);
X = cell2mat(cursor.Data);
SIZE = size(X);
for i=1:SIZE(1)
   if X(i,1) > 0
   else
        X(i,1)=0;
   end
   if X(i,2) > 0
   else
        X(i,2)=0;
   end
   if X(i,SIZE(2)) >= 50
        X(i,SIZE(2))=1;
   else
        X(i,SIZE(2))=0;
   end
end
y = X(:,SIZE(2));
suffix = ones(SIZE(1),1);
X = [suffix X(:,1:SIZE(2)-1)];