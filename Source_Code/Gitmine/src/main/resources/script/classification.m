%% Initialization
clear ; close all; clc
X = importdata('X.mat');
y = importdata('y.mat');
[m,n] = size(X);


%  Set options for fminunc
options = optimset('GradObj', 'on', 'MaxIter', 600);

% Initialize fitting parameters
initial_theta = zeros(n, 1);

%  Run fminunc to obtain the optimal theta
%  This function will return theta and the cost 
[theta, cost, existFlag] = ...
	fminunc(@(t)(costFunction(t, X, y)), initial_theta, options);