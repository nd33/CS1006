PROC TRIANGLE ( SIZE )
	IF SIZE > 5 THEN
		TRIANGLE( SIZE / 2 )
		FORWARD SIZE
		RIGHT 120
		TRIANGLE( SIZE / 2 )
		FORWARD SIZE
		RIGHT 120
		TRIANGLE( SIZE / 2 )
		FORWARD SIZE
		RIGHT 120
	     ELSE
		FORWARD 0
	     ENDIF

	PROC MAIN( VOID )
	TRIANGLE fdfds 250 ds3


