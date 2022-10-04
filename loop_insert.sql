create procedure loop_insert(IN cnt int)
begin
	declare i int default 1;
	while (i <= cnt) do
	insert into board(title, userid, contents)
	values ('aa','bb','cc');
	insert into board(title, userid, contents)
	values ('글제목','김길동','가나다');
	insert into board(title, userid, contents)
	values ('1201','김민수','글내용');
	set i = i + 1;
	end while;
end;

delete from board;

call loop_insert(221004);
