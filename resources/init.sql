USE ssafit;

INSERT INTO
    video (
        youtube_id,
        title,
        channel_name,
        fitpart_name
    )
VALUES
    (
        'gMaB-fG4u4g',
        '전신 다이어트 최고의 운동 [칼소폭 찐 핵핵매운맛]',
        'ThankyouBUBU',
        '전신'
    ),
    (
        'swRNeYw1JkY',
        '하루 15분! 전신 칼로리 불태우는 다이어트 운동',
        'ThankyouBUBU',
        '전신'
    ),
    (
        '54tTYO-vU2E',
        '상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]',
        'ThankyouBUBU',
        '상체'
    ),
    (
        'QqqZH3j_vH0',
        '상체비만 다이어트 최고의 운동 [상체 핵매운맛]',
        'ThankyouBUBU',
        '상체'
    ),
    (
        'tzN6ypk6Sps',
        '하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]',
        '김강민',
        '하체'
    ),
    (
        'u5OgcZdNbMo',
        '저는 하체 식주의자 입니다',
        'GYM종국',
        '하체'
    ),
    (
        'PjGcOP-TQPE',
        '11자복근 복부 최고의 운동 [복근 핵매운맛]',
        'ThankyouBUBU',
        '복부'
    ),
    (
        '7TLk7pscICk',
        '(Sub)누워서하는 5분 복부운동!! 효과보장! (매일 2주만 해보세요!)',
        'SomiFit',
        '복부'
    ),
    (
        'cMkZ6A7wngk',
        '[ENG/ 전신 올인원 운동] 유튜브에서 다이어트 전신운동 찾았어요? 이제 딱 이거 하나만 해요! l diet workoutㅣ다노티비',
        'DanoTV',
        '전신'
    ),
    (
        '4kZHHPH6heY',
        'NO 층간소음 - 고민없이 하나로 끝내는 전신운동 근력 유산소 - 운동 못한 날 죄책감 씻어줄 30분 홈트',
        'Bigsis',
        '전신'
    );

INSERT INTO
    user (user_id, PASSWORD)
VALUES
    ('ssafy', 'admin'),
    ('ssafy2', 'admin'),
    ('hailey', '1q2w3e4r9qks!'),
    ('eduharry', 'eduharry'),
    ('seul0208', 'seul0208'),
    ('fitness_lover', 'Fit2023!'),
    ('workout_king', 'StrongP@ss123'),
    ('health_guru', 'Wellness#456'),
    ('gym_rat99', 'LiftHeavy789!'),
    ('active_life', 'MoveDaily2023'),
    ('muscle_builder', 'Protein$hake22'),
    ('cardio_queen', 'RunFast!234'),
    ('yoga_master', 'Namaste@567'),
    ('sporty_spice', 'Athletics#890'),
    ('iron_pumper', 'GymLife2023!'),
    ('wellness_coach', 'Healthy$Living1'),
    ('fit_fanatic', 'Exercise@Home2'),
    ('strong_soul', 'PowerLift#345'),
    ('endurance_pro', 'Marathon2023!'),
    ('flex_master', 'StretchDaily$6'),
    ('pilates_pro', 'Core$trength7'),
    ('training_beast', 'WorkoutHard!8'),
    ('health_junkie', 'Nutrition@910'),
    ('fitness_freak', 'SweatDaily#11'),
    ('gym_enthusiast', 'LiftWeights!12');


INSERT INTO
    review (youtube_id, title, author_id, content)
VALUES
    (
        'gMaB-fG4u4g',
        '힘들어요',
        'ssafy',
        '엉엉잉잉앙앙'
    ),
    (
        'cMkZ6A7wngk',
        '운동해서 놀러갈래',
        'ssafy',
        '한강 피크닉가자 낄'
    ),
    (
        'cMkZ6A7wngk',
        '운동해서 놀러갈래',
        'ssafy2',
        '사실 집이 제일 좋아 후엥'
    ), 
    (
		'4kZHHPH6heY',
        '그아아악', 
        'ssafy',
        '우에에에엑'
    ), 
	(
		'4kZHHPH6heY',
        '힝구리퐁퐁', 
        'ssafy2',
        '배고파요'
    );

INSERT INTO
    likedvideo (youtube_id, user_id)
VALUES
    ('gMaB-fG4u4g', 'ssafy'),
    ('PjGcOP-TQPE', 'ssafy'),
    ('PjGcOP-TQPE', 'hailey');

INSERT INTO
    following (follower_id, followee_id)
VALUES
    ('ssafy', 'hailey'),
    ('ssafy', 'eduharry'),
    ('hailey', 'ssafy'),
    ('eduharry', 'ssafy'),
    ('ssafy', 'fitness_lover'),
    ('ssafy', 'workout_king'),
    ('ssafy', 'health_guru'),
    ('ssafy', 'gym_rat99'),
    ('ssafy', 'active_life'),
    ('hailey', 'muscle_builder'),
    ('hailey', 'cardio_queen'),
    ('hailey', 'yoga_master'),
    ('hailey', 'sporty_spice'),
    ('eduharry', 'iron_pumper'),
    ('eduharry', 'wellness_coach'),
    ('eduharry', 'fit_fanatic'),
    ('fitness_lover', 'ssafy'),
    ('workout_king', 'ssafy'),
    ('health_guru', 'hailey'),
    ('gym_rat99', 'hailey'),
    ('active_life', 'eduharry'),
    ('muscle_builder', 'eduharry'),
    ('cardio_queen', 'seul0208'),
    ('yoga_master', 'seul0208'),
    ('sporty_spice', 'strong_soul'),
    ('iron_pumper', 'endurance_pro'),
    ('wellness_coach', 'flex_master'),
    ('fit_fanatic', 'pilates_pro'),
    ('strong_soul', 'training_beast'),
    ('endurance_pro', 'health_junkie'),
    ('flex_master', 'fitness_freak'),
    ('pilates_pro', 'gym_enthusiast');
