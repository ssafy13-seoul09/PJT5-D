INSERT INTO
    user (user_id, PASSWORD)
VALUES
    ('ssafy', 'admin'),
    ('ssafy2', 'admin'),
    ('hailey', '1q2w3e4r9qks!'),
    ('eduharry', 'eduharry');

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
    ('eduharry', 'ssafy');

