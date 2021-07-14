from django.db import models

# Create your models here.
from django.contrib.auth.models import AbstractUser
# Create your models here.

class CustomUser(AbstractUser): #기본 칼럼을 상속
    #추가 칼럼 확장
    nickname = models.CharField(max_length=100) #닉네임
    university = models.CharField(max_length=50) #대학정보
    address = models.CharField(max_length=20) #거주지 정보
    hashTag1 = models.CharField(max_length=20) #해쉬태그 관심사 123
    hashTag2 = models.CharField(max_length=20)
    hashTag3 = models.CharField(max_length=20)