document.addEventListener('DOMContentLoaded', function() {
  const components = document.querySelectorAll('.follow-button-component');
  
  components.forEach(component => {
    const targetId = component.getAttribute('data-target-id');
    const followBtn = component.querySelector('.follow-btn');
    const unfollowBtn = component.querySelector('.unfollow-btn');
    
    if (!targetId || !followBtn || !unfollowBtn) return;
    
    fetch('user?act=checkfollow&id=' + targetId)
      .then(response => {
        if (!response.ok) {
          throw new Error('서버 응답 오류');
        }
        return response.text();
      })
      .then(result => {
        const isfollowed = result === 'true';
        updateButtonState(followBtn, unfollowBtn, isfollowed);
      })
      .catch(error => {
        console.error('팔로우 상태 확인 중 오류 발생:', error);
      });
    
    // 좋아요 버튼 클릭 이벤트
    followBtn.addEventListener('click', function(e) {
      e.preventDefault();
      fetch('user?act=follow&id=' + targetId)
        .then(response => {
          if (!response.ok) {
            throw new Error('서버 응답 오류');
          }
          updateButtonState(followBtn, unfollowBtn, true);
        })
        .catch(error => {
          console.error('팔로우 중 오류 발생:', error);
        });
    });
    
    // 좋아요 취소 버튼 클릭 이벤트
    unfollowBtn.addEventListener('click', function(e) {
      e.preventDefault();
      fetch('user?act=unfollow&id=' + targetId)
        .then(response => {
          if (!response.ok) {
            throw new Error('서버 응답 오류');
          }
          updateButtonState(followBtn, unfollowBtn, false);
        })
        .catch(error => {
          console.error('팔로우 중 오류 발생:', error);
        });
    });
  });
  
  // 버튼 상태 업데이트 함수
  function updateButtonState(followBtn, unfollowBtn, isfollowed) {
    if (isfollowed) {
      followBtn.style.display = 'none';
      unfollowBtn.style.display = '';
    } else {
      followBtn.style.display = 'i';
      unfollowBtn.style.display = 'none';
    }
  }
});
