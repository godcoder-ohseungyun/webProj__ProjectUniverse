from django.urls import path

from django.contrib.auth import *

#메인 urls.py 파일에서 경로에 대한 처리를 한 상태, 빈 문자열('')을 인자로 준다.
from . import views

urlpatterns = [ 
    path('login/',views.loginP, name='login'),
    path('signUp/',views.signUpP, name='signUp'),
    path('logout/',views.logoutP,name='logout')
]

