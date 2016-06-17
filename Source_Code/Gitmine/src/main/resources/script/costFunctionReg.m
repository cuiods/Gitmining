function [J, grad] = costFunctionReg(theta, X, y, lambda)
%COSTFUNCTIONREG Compute cost and gradient for logistic regression with regularization
%   J = COSTFUNCTIONREG(theta, X, y, lambda) computes the cost of using
%   theta as the parameter for regularized logistic regression and the
%   gradient of the cost w.r.t. to the parameters. 
[m,n] = size(X);
% Initialize some useful values
m = length(y); % number of training examples

% You need to return the following variables correctly 
J = 0;
grad = zeros(size(theta));

theta2 = theta(2:n,:);
J = sum(-y.*log(sigmoid(X*theta))-(1-y).*log(1-sigmoid(X*theta)))/m + sum(theta2.*theta2)*(lambda/(2*m));

grad = 1 / m * (X' * (sigmoid(X*theta) - y));

for i = 2:n
  grad(i) += theta(i)*lambda/m;
end


end
