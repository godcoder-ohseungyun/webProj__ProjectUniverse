from django.urls import path

from . import views

#메인 urls.py 파일에서 경로에 대한 처리를 한 상태, 빈 문자열('')을 인자로 준다.

urlpatterns = [ 
    path('', views.startP,name="startPage"),
]

