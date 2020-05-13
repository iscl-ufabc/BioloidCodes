%% ABRINDO AS IMAGENS
clear
clc

load('dados.mat')

heigth = 160;
width = 120;

inicio = 59;
trainPic = 100;

reduz = 1/4;

pasta = '.\VideoOpenCV\';
tipo = 'BCG.jpg';

quant=1;

imagens = zeros(width*reduz,heigth*reduz,quant*trainPic);
labels = zeros(quant*trainPic,3);
result1 = zeros(111,4);
result2 = zeros(111,4);
result3 = zeros(111,4);

for i = inicio:trainPic
    imagens(:,:,i) = imresize(imread(strcat(pasta,int2str(i-1),tipo)),reduz);
    labels(i,:) = dados(i,:);
end

% pasta = '.\VideoOpenCV\';
% tipo = 'BCGL.jpg';
% for i = 1:trainPic
%     imagens(:,:,i+50) = imresize(imread(strcat(pasta,int2str(i-1),tipo)),reduz);
%    labels(i+50,:) = dados(i,:);
% end

% pasta = '.\LaplacianFilter\';
% tipo = 'BC.jpg';
% for i = 1:trainPic
%     imagens(:,:,i+100) = imresize(imread(strcat(pasta,int2str(i-1),tipo)),reduz);
%     labels(i+100,:) = dados(i,:);
% end

imagens = reshape(imagens,width*reduz*heigth*reduz,trainPic*quant);
labels = labels';

save('labels.mat','labels');
save('imagens.mat','imagens');
%% TREINANDO A REDE

r=11;
netR=feedforwardnet(r);
netR.trainParam.max_fail = 100;

%load('netR.mat')

netR=train(netR,imagens(:,59:100),labels);

save('netR','netR');

testePic = imagens(:,inicio)';
save('testePic.mat','testePic');

%% SALVANDO OS PESOS PARA O JAVA

bR= [cell2mat(netR.b(1,:))' cell2mat(netR.b(2,:))'];
IWR = cell2mat(netR.IW(1,:));
LWR = cell2mat(netR.LW(2,1));
 
WR = [bR(:,1) IWR(1,:)];

for i = 2:r
    WR = [WR bR(:,i) IWR(i,:)];
end

j = i;
for k = i+1:i+3
    WR = [WR bR(:,k) LWR(k-j,:)];
end
save('WR.mat','WR');
%% VALIDAÇÃO

for j = 1:quant
    for i = inicio:trainPic
        if (j==1)
            result1(i,:) = [i-1 sim(netR,imagens(:,i))'];
        end
        if (j==2)
            result2(i,:) = [i-1 sim(netR,imagens(:,i+100))'];
        end
        if (j==3)
            result3(i,:) = [i-1 sim(netR,imagens(:,i+200))'];
        end
    end
end

pasta = '.\VideoOpenCV\';
tipo = 'BCG.jpg';
for i=100:110
    A = double(imresize(imread(strcat(pasta,int2str(i),tipo)),reduz));
    A = reshape(A,width*reduz*heigth*reduz,1);

    result1(i+1,:) = [i sim(netR,A)'];
end

pasta = '.\VideoOpenCV\';
tipo = 'BCGL.jpg';
for i=0:110
    A = double(imresize(imread(strcat(pasta,int2str(i),tipo)),reduz));
    A = reshape(A,width*reduz*heigth*reduz,1);

    result2(i+1,:) = [i sim(netR,A)'];
end

pasta = '.\LaplacianFilter\';
tipo = 'BC.jpg';
for i=0:110
    A = double(imresize(imread(strcat(pasta,int2str(i),tipo)),reduz));
    A = reshape(A,width*reduz*heigth*reduz,1);

    result3(i+1,:) = [i sim(netR,A)'];
end