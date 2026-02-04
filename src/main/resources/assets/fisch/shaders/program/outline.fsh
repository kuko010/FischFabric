#version 110

uniform sampler2D DiffuseSampler;
uniform sampler2D DepthSampler;

uniform vec2 InSize;
uniform float EdgeThickness;
uniform float EdgeStrength;
uniform vec3 OutlineColor;

varying vec2 texCoord;

// Sobel edge detection kernel
void main() {
    vec2 texelSize = EdgeThickness / InSize;

    // Sample depth values in a 3x3 grid around the current pixel
    float d00 = texture2D(DepthSampler, texCoord + vec2(-texelSize.x, -texelSize.y)).r;
    float d01 = texture2D(DepthSampler, texCoord + vec2(0.0, -texelSize.y)).r;
    float d02 = texture2D(DepthSampler, texCoord + vec2(texelSize.x, -texelSize.y)).r;

    float d10 = texture2D(DepthSampler, texCoord + vec2(-texelSize.x, 0.0)).r;
    float d12 = texture2D(DepthSampler, texCoord + vec2(texelSize.x, 0.0)).r;

    float d20 = texture2D(DepthSampler, texCoord + vec2(-texelSize.x, texelSize.y)).r;
    float d21 = texture2D(DepthSampler, texCoord + vec2(0.0, texelSize.y)).r;
    float d22 = texture2D(DepthSampler, texCoord + vec2(texelSize.x, texelSize.y)).r;

    // Sobel operator for edge detection
    float sobelX = d00 + 2.0 * d10 + d20 - d02 - 2.0 * d12 - d22;
    float sobelY = d00 + 2.0 * d01 + d02 - d20 - 2.0 * d21 - d22;

    float edge = sqrt(sobelX * sobelX + sobelY * sobelY);
    edge = clamp(edge * EdgeStrength, 0.0, 1.0);

    // Get original color
    vec4 color = texture2D(DiffuseSampler, texCoord);

    // Mix outline color with original color based on edge strength
    gl_FragColor = vec4(mix(color.rgb, OutlineColor, edge), color.a);
}
