document.addEventListener('DOMContentLoaded', function() {
  const components = document.querySelectorAll('.like-button-component');
  
  components.forEach(component => {
    const videoId = component.getAttribute('data-video-id');
    const likeBtn = component.querySelector('.like-btn');
    const unlikeBtn = component.querySelector('.unlike-btn');
    
    if (!videoId || !likeBtn || !unlikeBtn) return;
    
    fetch('user?act=checklike&id=' + videoId)
      .then(response => {
        if (!response.ok) {
          throw new Error('서버 응답 오류');
        }
        return response.text();
      })
      .then(result => {
        const isLiked = result === 'true';
        updateButtonState(likeBtn, unlikeBtn, isLiked);
      })
      .catch(error => {
        console.error('좋아요 상태 확인 중 오류 발생:', error);
      });
    
    // 좋아요 버튼 클릭 이벤트
    likeBtn.addEventListener('click', function(e) {
      e.preventDefault();
      fetch('user?act=likevideo&id=' + videoId)
        .then(response => {
          if (!response.ok) {
            throw new Error('서버 응답 오류');
          }
          updateButtonState(likeBtn, unlikeBtn, true);
        })
        .catch(error => {
          console.error('좋아요 추가 중 오류 발생:', error);
        });
    });
    
    // 좋아요 취소 버튼 클릭 이벤트
    unlikeBtn.addEventListener('click', function(e) {
      e.preventDefault();
      fetch('user?act=unlikevideo&id=' + videoId)
        .then(response => {
          if (!response.ok) {
            throw new Error('서버 응답 오류');
          }
          updateButtonState(likeBtn, unlikeBtn, false);
        })
        .catch(error => {
          console.error('좋아요 취소 중 오류 발생:', error);
        });
    });
  });
  
  // 버튼 상태 업데이트 함수
  function updateButtonState(likeBtn, unlikeBtn, isLiked) {
    if (isLiked) {
      likeBtn.style.display = 'none';
      unlikeBtn.style.display = '';
    } else {
      likeBtn.style.display = '';
      unlikeBtn.style.display = 'none';
    }
  }
});
